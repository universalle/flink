package com.cnhindustrial.telemetry;

import com.cnhindustrial.telemetry.factory.GeoMesaCassandraDataStoreFactory;
import com.cnhindustrial.telemetry.model.TelemetryFeatureWrapper;
import com.cnhindustrial.telemetry.model.TelemetryStatusSimpleFeatureTypeBuilder;
import com.cnhindustrial.telemetry.model.TelemetryValueSimpleFeatureTypeBuilder;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.typeutils.runtime.kryo.KryoSerializer;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.operators.StreamingRuntimeContext;
import org.apache.flink.streaming.runtime.tasks.ProcessingTimeCallback;
import org.apache.flink.streaming.runtime.tasks.ProcessingTimeService;
import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;

import org.locationtech.geomesa.index.geotools.GeoMesaFeatureStore;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class GeoMesaBufferedSink extends RichSinkFunction<TelemetryFeatureWrapper> implements CheckpointedFunction, ProcessingTimeCallback, Serializable {

    private static final long serialVersionUID = -667478325867719765L;

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoMesaBufferedSink.class);

    private static GeoMesaCassandraDataStoreFactory dataStoreFactory = new GeoMesaCassandraDataStoreFactory();

    //Flink Counter
    private transient Counter counter;

    //Used for saving telemetry status features to DB
    private transient GeoMesaFeatureStore statusFeatureStore;

    //Used for saving telemetry value features to DB
    private transient GeoMesaFeatureStore valueFeatureStore;

    //Used as Timer for sending partial messages
    private transient ProcessingTimeService processingTimeService;

    //Here is our batch with messages
    private List<TelemetryFeatureWrapper> bufferedMessages;

    //List for saving state, as described in Flink documentation
    private transient ListState<TelemetryFeatureWrapper> checkpointedState;

    //Time in millis for timer, triggered every 30 minutes
    private static final int INACTIVE_BUCKET_CHECK_INTERVAL = 30 * 60 * 1000;

    //How messages is stored in batch,
    //Mind that each parallel execution
    //has it own batch with this THRESHOLD
    private static final int GENERAL_THRESHOLD = 100;

    private int thresholdPerParallelSubtag;

    public GeoMesaBufferedSink() {
        bufferedMessages = new ArrayList<>(GENERAL_THRESHOLD);
    }

    @Override
    public void invoke(TelemetryFeatureWrapper value, Context context) {
        this.bufferedMessages.add(value);
        this.counter.inc();
        if (this.bufferedMessages.size() >= thresholdPerParallelSubtag) {
            storeMessageToGeomesa();
            this.bufferedMessages.clear();
        }
    }

    /**
     * Create checkpoint from our list of messages
     */
    @Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception {
        this.checkpointedState.clear();
        this.checkpointedState.addAll(this.bufferedMessages);
    }

    /**
     * Restore messages from checkpoint
     */
    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception {
        KryoSerializer serializer = new KryoSerializer<>(TelemetryFeatureWrapper.class, getRuntimeContext().getExecutionConfig());
        ListStateDescriptor<TelemetryFeatureWrapper> descriptor = new ListStateDescriptor<TelemetryFeatureWrapper>("buffered-elements", serializer);
        this.checkpointedState = context.getOperatorStateStore().getListState(descriptor);
        if (context.isRestored()) {
            for (TelemetryFeatureWrapper element : this.checkpointedState.get()) {
                this.bufferedMessages.add(element);
            }
        }
    }

    /**
     * Setup Geomesa
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        LOGGER.debug("Configuring CanPlug sink function.");
        //Initialize Flink Counter
        counter = getRuntimeContext().getMetricGroup().counter("counterBatchedMessages");

        //Initialize feature storage (Geomesa)
        Map<String, String> globalJobConfiguration = getRuntimeContext().getExecutionConfig().getGlobalJobParameters().toMap();
        Map<String, String> allParameters = new HashMap<>(globalJobConfiguration);

        String cassandraContactPoint = allParameters.getOrDefault("cassandra.contact.point", "127.0.0.1:9042");
        String cassandraKeyspace = allParameters.getOrDefault("cassandra.keyspace", "mykeyspace");
        String cassandraCatalog = allParameters.getOrDefault("cassandra.catalog", "telemetry");

        allParameters.put("cassandra.contact.point", cassandraContactPoint);
        allParameters.put("cassandra.keyspace", cassandraKeyspace);
        allParameters.put("cassandra.catalog", cassandraCatalog);
        allParameters.put("geomesa.batchwriter.maxthreads", "10");
        allParameters.put("geomesa.batchwriter.latency.millis", "60000");
        allParameters.put("geomesa.batchwriter.memory", "52428800");
        allParameters.put("geomesa.batchwriter.timeout.millis", "600000");

        //Geomesa Data Store used for getting GeoMesaFeatureStore
        DataStore datastore = dataStoreFactory.apply(allParameters);
        SimpleFeatureType feature = datastore.getSchema("telemetry_status");
        if (feature == null) {
            datastore.createSchema(TelemetryStatusSimpleFeatureTypeBuilder.getFeatureType());
        }
        feature = datastore.getSchema("telemetry_value");
        if (feature == null) {
            datastore.createSchema(TelemetryValueSimpleFeatureTypeBuilder.getFeatureType());
        }
        statusFeatureStore = (GeoMesaFeatureStore) datastore.getFeatureSource(TelemetryStatusSimpleFeatureTypeBuilder.getFeatureType().getTypeName());
        valueFeatureStore = (GeoMesaFeatureStore) datastore.getFeatureSource(TelemetryValueSimpleFeatureTypeBuilder.getFeatureType().getTypeName());

        //Initialize ProcessingTimeService
        this.processingTimeService = ((StreamingRuntimeContext) getRuntimeContext()).getProcessingTimeService();

        //Current timestamp
        final long currentProcessingTime = this.processingTimeService.getCurrentProcessingTime();

        //Set up trigger for sending partial messages
        this.processingTimeService.registerTimer(currentProcessingTime + INACTIVE_BUCKET_CHECK_INTERVAL, this);

        //Each parallel sub-task have it' own counter, need to divide general on each sub-task
        this.thresholdPerParallelSubtag = GENERAL_THRESHOLD / getRuntimeContext().getNumberOfParallelSubtasks();
    }

    /**
     * Here is logic of sending partial messages
     * And set up time trigger for next iteration
     */
    @Override
    public void onProcessingTime(long timestamp) {
        //Get current timestamp
        long currentProcessingTime = processingTimeService.getCurrentProcessingTime();

        //Sending partial messages
        sendPartialMessages();

        //Set up time trigger for next iteration
        this.processingTimeService.registerTimer(currentProcessingTime + INACTIVE_BUCKET_CHECK_INTERVAL, this);
    }

    private void sendPartialMessages() {
        storeMessageToGeomesa();
        this.bufferedMessages.clear();
    }

    private void storeMessageToGeomesa() {
        List<SimpleFeature> statuses = this.bufferedMessages.stream()
                .map(TelemetryFeatureWrapper::getStatusFeature)
                .collect(Collectors.toList());
        SimpleFeatureCollection statusesCollection = DataUtilities.collection(statuses);
        statusFeatureStore.addFeatures(statusesCollection);

        List<SimpleFeature> values = this.bufferedMessages.stream()
                .map(TelemetryFeatureWrapper::getValueFeatures)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        SimpleFeatureCollection valuesCollection = DataUtilities.collection(values);
        valueFeatureStore.addFeatures(valuesCollection);
    }
}
