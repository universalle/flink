package com.cnhindustrial.telemetry.function;

import com.cnhindustrial.telemetry.common.model.ControllerDto;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.sources.DefinedRowtimeAttributes;
import org.apache.flink.table.sources.RowtimeAttributeDescriptor;
import org.apache.flink.table.sources.StreamTableSource;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ControllerTableSource implements StreamTableSource<ControllerDto>,
        DefinedRowtimeAttributes,
        Serializable {

    private static final long serialVersionUID = 5111575611435668091L;

    private final DataStream<ControllerDto> sourceStream;

    public ControllerTableSource(DataStream<ControllerDto> sourceStream) {
        this.sourceStream = sourceStream;
    }

    @Override
    public List<RowtimeAttributeDescriptor> getRowtimeAttributeDescriptors() {
        return Collections.emptyList();
    }

    @Override
    public DataStream<ControllerDto> getDataStream(StreamExecutionEnvironment streamExecutionEnvironment) {
        return sourceStream;
    }

    @Override
    public TableSchema getTableSchema() {
        return new TableSchema.Builder().build();
    }
}
