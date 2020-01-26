package com.cnhindustrial.telemetry.pipeline;

import com.cnhindustrial.telemetry.common.model.TelemetryDto;
import com.cnhindustrial.telemetry.converter.TelemetryConverter;
import com.cnhindustrial.telemetry.function.DeserializeMapFunction;
import com.cnhindustrial.telemetry.function.SideOutputProcessFunction;
import com.cnhindustrial.telemetry.function.TelemetryDtoConverter;
import com.cnhindustrial.telemetry.function.TelemetryValidationFunction;
import com.cnhindustrial.telemetry.model.TelemetryFeatureWrapper;
import com.twitter.chill.java.UnmodifiableMapSerializer;

import de.javakaffee.kryoserializers.CollectionsSingletonListSerializer;
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.DiscardingSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.OutputTag;
import org.geotools.util.UnmodifiableArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngestPipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngestPipeline.class);

    private final SourceFunction<byte[]> telemetryDataSource;
    private final DataStreamSource<byte[]> controllerDataSource;
    private final SinkFunction<TelemetryFeatureWrapper> machineDataSink;
    private final SinkFunction<TelemetryDto> deadLetterSink;
    private final SinkFunction<TelemetryDto> logTelemetry;

    IngestPipeline(SourceFunction<byte[]> telemetryDataSource,
                   DataStreamSource<byte[]> controllerDataSource,
                   SinkFunction<TelemetryFeatureWrapper> machineDataSink,
                   SinkFunction<TelemetryDto> deadLetterSink,
                   SinkFunction<TelemetryDto> logTelemetry) {
        this.telemetryDataSource = telemetryDataSource;
        this.controllerDataSource = controllerDataSource;
        this.machineDataSink = machineDataSink;
        this.deadLetterSink = deadLetterSink;
        this.logTelemetry = logTelemetry;
    }

    public static void main(String[] args) throws Exception {

        ParameterTool parameters = new ParameterToolBuilder()
                .mergeEnvironmentVariables()
                .mergeCommandLineArguments(args)
                .build();

        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
        configureExecutionEnvironment(see.getConfig(), parameters);

        FunctionFactory functionFactory = new FunctionFactory(parameters);

        IngestPipeline ingestPipeline = new IngestPipeline(
                functionFactory.getTelemetryDataSource(),
                functionFactory.getControllerDataSource(see),
                functionFactory.getMachineDataSink(),
                functionFactory.getDeadLetterSink(),
                new DiscardingSink<>());

        ingestPipeline.build(see);
        ingestPipeline.execute(see);
    }

    static void configureExecutionEnvironment(ExecutionConfig execConfig, ParameterTool parameters) throws Exception {
        execConfig.setGlobalJobParameters(parameters);

        Class<?> unmodifiableCollection = Class.forName("java.util.Collections$UnmodifiableCollection");
        execConfig.addDefaultKryoSerializer(unmodifiableCollection, UnmodifiableCollectionsSerializer.class);

        Class<?> unmodifiableMap = Class.forName("java.util.Collections$UnmodifiableMap");
        execConfig.addDefaultKryoSerializer(unmodifiableMap, UnmodifiableMapSerializer.class);

        execConfig.addDefaultKryoSerializer(UnmodifiableArrayList.class, CollectionsSingletonListSerializer.class);
    }

    /**
     * Build the pipeline.
     */
    void build(StreamExecutionEnvironment see) {
        LOGGER.debug("Building Ingest Pipeline");

        DataStream<byte[]> rawMessageStream = see
                .addSource(telemetryDataSource)
                .name("Message From Event Hub")
                .uid("eventhub-source");

//        DataStream<byte[]> rawControllerStream = controllerDataSource
//                .name("Message from Blob Storage")
//                .uid("blob-storage-source");

        OutputTag<TelemetryDto> deadLetterOutput = new OutputTag<>("invalid-messages", TypeInformation.of(TelemetryDto.class));

        SingleOutputStreamOperator<TelemetryDto> validatedTelemetryStream = rawMessageStream
                .map(new DeserializeMapFunction<>(TelemetryDto.class))
                .name("Deserialize Telemetry")
                .uid("deserialize-telemetry")
                .process(new TelemetryValidationFunction(deadLetterOutput))
                .name("Telemetry Validation Output")
                .uid("telemetry-validation-output");

        validatedTelemetryStream.getSideOutput(deadLetterOutput)
                .addSink(deadLetterSink)
                .name("Sink Invalid Telemetry data to Dead Letter Queue")
                .uid("dead-letter-queue-sink");

        OutputTag<TelemetryDto> telemetryLogOutput = new OutputTag<>("telemetry-cache", TypeInformation.of(TelemetryDto.class));
        SingleOutputStreamOperator<TelemetryDto> flattenTelemetryStream = validatedTelemetryStream
                .process(new SideOutputProcessFunction<>(telemetryLogOutput, new TelemetryDtoConverter(), TelemetryDto.class))
                .name("Telemetry Side Output")
                .uid("telemetry-side-output");

        flattenTelemetryStream.getSideOutput(telemetryLogOutput)
                .addSink(logTelemetry)
                .name("Log Telemetry")
                .uid("tempo-log-telemetry");

//        SingleOutputStreamOperator<ControllerDto> controllerStream = rawControllerStream
//                .map(new DeserializeMapFunction<>(ControllerDto.class))
//                .name("Deserialize Controller Data")
//                .uid("deserialize-controller");

//        StreamTableSource<FlattenTelemetryDto> telemetryTableSource = new FlattenTelemetryTableSource(flattenTelemetryStream);
//        StreamTableSource<ControllerDto> controllerTableSource = new ControllerTableSource(controllerStream);

//        StreamTableEnvironment ste = StreamTableEnvironment.create(see);

//        Table telemetryTable = ste.fromDataStream(flattenTelemetryStream, "deviceid, assetId");
//        Table controllerTable = ste.fromDataStream(controllerStream, "id, description");

//        Table mergedTable = controllerTable.join(telemetryTable, "id = assetId"); // TODO the join condition is wrong

//        DataStream<ControllerDto> controllerMergeStream = ste.toAppendStream(mergedTable, ControllerDto.class);

        DataStream<TelemetryFeatureWrapper> telemetryFeatureStream = flattenTelemetryStream.getSideOutput(deadLetterOutput)
                .map(new TelemetryConverter())
                .name("Telemetry Feature converter")
                .uid("telemetry-feature-converter");

        telemetryFeatureStream.addSink(machineDataSink)
                .name("Sink Telemetry data to Geomesa")
                .uid("telemetry-geomesa-sink");

//        DataStream<SimpleFeatureImpl> controllerFeatureStream = controllerStream
//                .map(new GeomesaControllerFeatureConverter())
//                .name("Controller Feature converter")
//                .uid("controller-feature-converter");

//        controllerFeatureStream.addSink(new PrintSinkFunction<>())
//                .name("Sink Controller data to Geomesa")
//                .uid("controller-geomesa-sink");
    }

    /**
     * Execute the pipeline.
     */
    void execute(StreamExecutionEnvironment see) throws Exception {
        LOGGER.debug("Starting pipeline execution.");
        see.execute("Ingest Pipeline");
    }
}
