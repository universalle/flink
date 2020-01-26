package com.cnhindustrial.telemetry;

import com.cnhindustrial.telemetry.factory.GeoMesaCassandraDataStoreFactory;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.geotools.data.DataStore;
import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;

/**
 * Flink stream data source emitting SimpleFeature queried from GeoMesa.
 */
public class GeoMesaSource extends RichSourceFunction<SimpleFeature> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoMesaSource.class);
    private static final long serialVersionUID = 3764587383375439614L;

    private final Query query;
    private transient DataStore datastore;

    private volatile boolean isRunning = true;

    public GeoMesaSource(Query query) {
        this.query = query;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        LOGGER.debug("Configuring GeoMesa source function.");
        ExecutionConfig executionConfig = getRuntimeContext().getExecutionConfig();
        Map<String, String> configuration = executionConfig.getGlobalJobParameters().toMap();
        datastore = getDataStoreFactory().apply(configuration);
    }

    @Override
    public void run(SourceContext<SimpleFeature> context) throws Exception {
        try (FeatureReader<SimpleFeatureType, SimpleFeature> reader = datastore.getFeatureReader(query, Transaction.AUTO_COMMIT)) {
            while (isRunning && reader.hasNext()) {
                LOGGER.debug("Reading GeoMesa feature.");

                SimpleFeature simpleFeature = reader.next();
                context.collect(simpleFeature);
            }
        }
    }

    @Override
    public void cancel() {
        LOGGER.debug("Stopping GeoMesa source function.");
        isRunning = false;
    }

    Function<Map<String, String>, DataStore> getDataStoreFactory() {
        return new GeoMesaCassandraDataStoreFactory();
    }
}
