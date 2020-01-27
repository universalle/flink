package com.cnhindustrial.telemetry.pipeline;

import com.cnhindustrial.telemetry.GeoMesaBufferedSink;
import com.cnhindustrial.telemetry.common.json.FileBytesInputFormat;
import com.cnhindustrial.telemetry.common.model.TelemetryDto;

import com.cnhindustrial.telemetry.model.TelemetryFeatureWrapper;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.ContinuousFileMonitoringFunction;
import org.apache.flink.streaming.api.functions.source.ContinuousFileReaderOperator;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for different {@link SourceFunction} and {@link SinkFunction} that serve as entry and exit points for Flink pipeline.
 */
class FunctionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionFactory.class);

    private static final int DEFAULT_BLOB_STORAGE_MONITOR_PARALLELISM = 1;
    private static final int DEFAULT_BLOB_STORAGE_READER_PARALLELISM = 2;
    private static final long DEFAULT_BLOB_STORAGE_MONITOR_INTERVAL_MS = 5000;

    private final ParameterTool parameters;

    FunctionFactory(ParameterTool parameters) {
        this.parameters = parameters;
    }

    SourceFunction<byte[]> getTelemetryDataSource() {
        if (parameters.has("event.hub.telemetry.endpoint")) {
            LOGGER.info("Building Telemetry Data source function connected to Azure Event Hub.");

            String connectionString = parameters.get("event.hub.telemetry.endpoint");
            LOGGER.debug("Azure Event Hub connection string: {}.", connectionString);

            return new KafkaConnector.Builder()
                    .buildEventHubConnector(connectionString)
                    .messageSource();
        } else {
            LOGGER.info("Building Telemetry Data source function connected to local Kafka.");

            return new KafkaConnector.Builder()
                    .buildKafkaConnector("users", "localhost:29092")
                    .messageSource();
        }
    }

    /**
     * Periodically monitor (every {@code interval} ms) the parameter-specified {@code filePath} for new data
     * and read content using {@link FileBytesInputFormat} as byte array.
     */
    DataStreamSource<byte[]> getControllerDataSource(StreamExecutionEnvironment see) {
        LOGGER.info("Building Controller Data source function connected to Azure Blob Storage.");

        String filePath = parameters.get("blob.storage.controller.data.path");
        long interval = parameters.has("blob.storage.controller.data.interval.ms")
                ? parameters.getInt("blob.storage.controller.data.interval.ms")
                : DEFAULT_BLOB_STORAGE_MONITOR_INTERVAL_MS;
        FileProcessingMode watchType = parameters.has("environment.test")
                ? FileProcessingMode.PROCESS_ONCE
                : FileProcessingMode.PROCESS_CONTINUOUSLY;

        LOGGER.debug("Azure Blob Storage path: {}, monitoring interval: {} ms, watch type: {}.",
                filePath, interval, watchType);

        FileBytesInputFormat inputFormat = new FileBytesInputFormat();
        inputFormat.setFilePath(filePath);

        ContinuousFileMonitoringFunction<byte[]> monitoringFunction =
                new ContinuousFileMonitoringFunction<>(inputFormat,
                        watchType,
                        DEFAULT_BLOB_STORAGE_MONITOR_PARALLELISM,
                        interval);

        ContinuousFileReaderOperator<byte[]> reader =
                new ContinuousFileReaderOperator<>(inputFormat);

        SingleOutputStreamOperator<byte[]> source = see.addSource(monitoringFunction)
                .name("Azure Blob Storage")
                .transform("Controller Dto Reader", inputFormat.getProducedType(), reader)
                .setParallelism(DEFAULT_BLOB_STORAGE_READER_PARALLELISM);

        return new DataStreamSource<>(source);
    }

    SinkFunction<TelemetryDto> getDeadLetterSink() {
        LOGGER.info("Building Dead Letter Queue sink function.");
        // TODO add real implementation
        return new PrintSinkFunction<>();
    }

    SinkFunction<TelemetryFeatureWrapper> getMachineDataSink() {
        LOGGER.info("Building Machine Data sink function connected to Geomesa.");
        return new GeoMesaBufferedSink();
    }
}
