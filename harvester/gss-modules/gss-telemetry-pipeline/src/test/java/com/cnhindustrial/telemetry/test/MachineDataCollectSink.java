package com.cnhindustrial.telemetry.test;

import com.cnhindustrial.telemetry.model.TelemetryFeatureWrapper;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Testing sink.
 */
public class MachineDataCollectSink<T> implements SinkFunction<T> {

    private static final long serialVersionUID = 428694870703857546L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineDataCollectSink.class);

    /**
     * must be static https://ci.apache.org/projects/flink/flink-docs-stable/dev/stream/testing.html#junit-rule-miniclusterwithclientresource
     */
    private static final List<Object> values = new ArrayList<>();

    @Override
    public synchronized void invoke(T value, Context context) {
//        LOGGER.info("SINK:\n> {}", value);
        values.add(value);
    }

    public List<T> getValues() {
        List<T> casted = values.stream()
                .map(v -> (T) v)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(casted);
    }

    public void clear() {
        values.clear();
    }
}
