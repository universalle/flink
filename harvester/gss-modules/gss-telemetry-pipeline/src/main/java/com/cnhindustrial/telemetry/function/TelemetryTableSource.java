package com.cnhindustrial.telemetry.function;

import com.cnhindustrial.telemetry.common.model.TelemetryDto;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.sources.DefinedRowtimeAttributes;
import org.apache.flink.table.sources.RowtimeAttributeDescriptor;
import org.apache.flink.table.sources.StreamTableSource;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TelemetryTableSource implements StreamTableSource<TelemetryDto>,
        DefinedRowtimeAttributes,
        Serializable {

    private static final long serialVersionUID = -7680095434990164658L;

    private final DataStream<TelemetryDto> sourceStream;

    public TelemetryTableSource(DataStream<TelemetryDto> sourceStream) {
        this.sourceStream = sourceStream;
    }

    @Override
    public DataStream<TelemetryDto> getDataStream(StreamExecutionEnvironment streamExecutionEnvironment) {
        return sourceStream;
    }

    @Override
    public TableSchema getTableSchema() {
        return new TableSchema.Builder().build();
    }

    @Override
    public List<RowtimeAttributeDescriptor> getRowtimeAttributeDescriptors() {
        return Collections.emptyList();
    }
}
