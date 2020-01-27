package com.cnhindustrial.telemetry.common.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

public class BaseDeserializationSchema<T> extends AbstractDeserializationSchema<T> {

    private static final long serialVersionUID = -3952414081189038371L;

    public static final String ZULU_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final ObjectMapper OBJECT_MAPPER = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Adding date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(ZULU_TIME_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
        mapper.setDateFormat(dateFormat);

        return mapper;
    }

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
