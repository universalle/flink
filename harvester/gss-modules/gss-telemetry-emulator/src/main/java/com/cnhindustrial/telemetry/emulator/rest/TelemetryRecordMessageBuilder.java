package com.cnhindustrial.telemetry.emulator.rest;

import org.springframework.stereotype.Component;

@Component
public class TelemetryRecordMessageBuilder extends MessageBuilder {

    private final String telemetryRecordTemplate = "{" +
            "  \"messageType\":\"Telemetry\"," +
            "  \"id\": 757," +
            "  \"offset\": 0," +
            "  \"resolution\": 1," +
            "  \"familyCode\": \"${familyCode}\"," +
            "  \"parameterType\": \"signal\"," +
            "  \"reportType\": \"Last\"," +
            "  \"samplingPeriod\": 0," +
            "  \"issigned\": false," +
            "  \"confidential\": true," +
            "  \"valueType\": \"Numeric\"," +
            "  \"last\": 81424.239," +
            "  \"translatedValue\": 81424.239" +
            "}";

    @Override
    public String getTemplate() {
        return telemetryRecordTemplate;
    }

    @Override
    public String getTemplateName() {
        return "telemetry";
    }
}
