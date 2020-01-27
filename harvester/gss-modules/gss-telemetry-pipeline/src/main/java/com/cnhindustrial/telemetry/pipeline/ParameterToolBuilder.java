package com.cnhindustrial.telemetry.pipeline;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParameterToolBuilder {

    private static final Map<String, String> DICTIONARY = new HashMap<>();

    static {
        DICTIONARY.put("event.hub.telemetry.endpoint", "EVENT_HUB_TELEMETRY_ENDPOINT");
        DICTIONARY.put("blob.storage.controller.data.path", "BLOB_STORAGE_CONTROLLER_DATA_PATH");
        DICTIONARY.put("blob.storage.controller.data.interval.ms", "BLOB_STORAGE_CONTROLLER_DATA_INTERVAL");
        DICTIONARY.put("cassandra.contact.point", "CASSANDRA_CONTACT_POINT");
        DICTIONARY.put("cassandra.keyspace", "CASSANDRA_KEYSPACE");
        DICTIONARY.put("cassandra.catalog", "CASSANDRA_CATALOG");
    }

    private ParameterTool parameterTool = ParameterTool.fromMap(Collections.emptyMap());
    private final Function<String, String> environmentVariableReader;

    public ParameterToolBuilder() {
        environmentVariableReader = (name -> System.getenv().get(name));
    }

    ParameterToolBuilder(Function<String, String> environmentVariableReader) {
        this.environmentVariableReader = environmentVariableReader;
    }

    public ParameterTool build() {
        return parameterTool;
    }


    public ParameterToolBuilder mergeEnvironmentVariables() {
        parameterTool = parameterTool.mergeWith(ParameterTool.fromMap(translateEnvironmentVariables()));
        return this;
    }

    private Map<String, String> translateEnvironmentVariables() {
        return DICTIONARY.entrySet().stream()
                .map(e -> new Tuple2<>(e.getKey(), environmentVariableReader.apply(e.getValue())))
                .filter(t2 -> StringUtils.isNoneBlank(t2.f1))
                .collect(Collectors.toMap(t2 -> t2.f0, t2 -> t2.f1));
    }

    public ParameterToolBuilder mergeCommandLineArguments(String[] args) {
        parameterTool = parameterTool.mergeWith(ParameterTool.fromArgs(args));
        return this;
    }
}
