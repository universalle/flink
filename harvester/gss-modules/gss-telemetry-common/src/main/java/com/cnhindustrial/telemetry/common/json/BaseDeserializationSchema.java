package com.cnhindustrial.telemetry.common.json;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BaseDeserializationSchema<T> extends AbstractDeserializationSchema<T> {

    private static final long serialVersionUID = -3952414081189038371L;

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperHelper.getObjectMapper();

    private final Class<? extends T> valueType;

    public BaseDeserializationSchema(Class<T> valueType) {
        super(valueType);
        this.valueType = valueType;
    }

    public T deserialize(byte[] source) throws IOException {
        return OBJECT_MAPPER.readValue(source, valueType);
    }

    public T deserialize(String source) throws IOException {
        return OBJECT_MAPPER.readValue(source, valueType);
    }
}
