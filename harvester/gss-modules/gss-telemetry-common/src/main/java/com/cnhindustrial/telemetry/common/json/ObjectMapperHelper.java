package com.cnhindustrial.telemetry.common.json;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class ObjectMapperHelper {

    public static final String ZULU_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Adding date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(ZULU_TIME_FORMAT);
        mapper.setDateFormat(dateFormat);

        return mapper;
    }
}
