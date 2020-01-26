package com.cnhindustrial.telemetry.pipeline;

import org.apache.flink.api.java.utils.ParameterTool;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ParameterToolBuilderTest {

    @Test
    void mergeEnvironmentVariables() {
        ParameterTool parameterTool = new ParameterToolBuilder(Function.identity())
                .mergeEnvironmentVariables()
                .build();

        assertThat(parameterTool.get("event.hub.telemetry.endpoint"), is("EVENT_HUB_TELEMETRY_ENDPOINT"));
    }

    @Test
    void mergeCommandLineArguments() {
        ParameterTool parameterTool = new ParameterToolBuilder()
                .mergeCommandLineArguments(new String[]{"-arg0", "value0", "--arg1", "value1"})
                .build();

        assertThat(parameterTool.get("arg0"), is("value0"));
        assertThat(parameterTool.get("arg1"), is("value1"));
    }
}