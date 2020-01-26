package com.cnhindustrial.telemetry.emulator.rest;

import org.springframework.stereotype.Component;

@Component
public class TelemetryMessageBuilder extends MessageBuilder {

    private static final String STATUS_MESSAGE_TEMPLATE = "{" +
            "\"messageType\":\"Status\"," +
            "\"pos\": {" +
            "  \"pdop\":-1," +
            "  \"current\":true," +
            "  \"satcount\":-1," +
            "  \"fixtype\":4," +
            "  \"alt\":64.475," +
            "  \"lon\":${lon}," +
            "  \"time\":\"${time}\"," +
            "  \"lat\":${lat}," +
            "  \"speed\":0," +
            "  \"direction\":118.78" +
            "}," +
            "\"status\":{" +
            "  \"duty\":11," +
            "  \"device\":\"on\"" +
            "}," +
            "\"networkinfo\":{" +
            "  \"rssi\":48," +
            "  \"MNC\":\"610\"," +
            "  \"NetworkStatus\":\"roaming\"," +
            "  \"connection\":\"UMTS\"," +
            "  \"MCC\":\"302\"," +
            "  \"operatorName\":\"DATA ONLY (Bell)\"" +
            "}," +
            "\"telemetryRecords\":[" +
            "   ${telemetryData}" +
            "]," +
            "\"reports\": null," +
            "\"events\": null," +
            "\"time\":\"${time}\"," +
            "\"deviceid\":\"81758916982\"," +
            "\"assetId\":\"${assetId}\"," +
            "\"from\":\"PCM_TELEMATICS\"," +
            "\"to\":\"SDP\"," +
            "\"techType\":\"696150782\"" +
            "}";

    @Override
    public String getTemplate() {
        return STATUS_MESSAGE_TEMPLATE;
    }

    @Override
    public String getTemplateName() {
        return "status";
    }
}
