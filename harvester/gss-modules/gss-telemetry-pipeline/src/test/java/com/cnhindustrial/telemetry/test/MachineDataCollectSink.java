package com.cnhindustrial.telemetry.test;

import com.cnhindustrial.telemetry.model.TelemetryFeatureWrapper;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Testing sink.
 */
public class MachineDataCollectSink implements SinkFunction<TelemetryFeatureWrapper> {

    private static final long serialVersionUID = 428694870703857546L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineDataCollectSink.class);

    /**
     * must be static
     * https://ci.apache.org/projects/flink/flink-docs-stable/dev/stream/testing.html#junit-rule-miniclusterwithclientresource
     */
    private static final List<TelemetryFeatureWrapper> values = new ArrayList<>();

    @Override
    public synchronized void invoke(TelemetryFeatureWrapper value, Context context) throws Exception {
        LOGGER.info("SINK:\n> {}", value);
        values.add(value);
    }

    public List<TelemetryFeatureWrapper> getValues() {
        return Collections.unmodifiableList(values);
    }

    public void clear() {
        values.clear();
    }
}
