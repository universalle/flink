package com.cnhindustrial.telemetry.function;

import com.cnhindustrial.telemetry.common.json.BaseDeserializationSchema;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.configuration.Configuration;

public class DeserializeMapFunction<T> extends RichMapFunction<byte[], T> implements ResultTypeQueryable<T> {

    private static final long serialVersionUID = 7543482211529843854L;

    private BaseDeserializationSchema<T> deserializationSchema;

    private final Class<T> classType;

    public DeserializeMapFunction(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        deserializationSchema = new BaseDeserializationSchema<>(classType);
    }

    @Override
    public T map(byte[] value) throws Exception {
        return deserializationSchema.deserialize(value);
    }

    @Override
    public TypeInformation<T> getProducedType() {
        return TypeInformation.of(classType);
    }
}
