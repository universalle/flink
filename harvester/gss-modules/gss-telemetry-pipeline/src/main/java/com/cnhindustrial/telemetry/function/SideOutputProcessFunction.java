package com.cnhindustrial.telemetry.function;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.function.Function;

public class SideOutputProcessFunction<T, S> extends ProcessFunction<T, S> implements ResultTypeQueryable<S> {

    private static final long serialVersionUID = -920720845189425766L;

    private final OutputTag<T> outputTag;
    private final Function<T, S> converter;
    private final Class<S> outClass;

    public SideOutputProcessFunction(OutputTag<T> outputTag, Function<T, S> converter, Class<S> outClass) {
        this.outputTag = outputTag;
        this.converter = converter;
        this.outClass = outClass;
    }

    @Override
    public void processElement(T value, Context context, Collector<S> collector) {
        // emit data to regular output
        collector.collect(converter.apply(value));

        // emit data to side output
        context.output(outputTag, value);
    }

    @Override
    public TypeInformation<S> getProducedType() {
        return TypeInformation.of(outClass);
    }
}
