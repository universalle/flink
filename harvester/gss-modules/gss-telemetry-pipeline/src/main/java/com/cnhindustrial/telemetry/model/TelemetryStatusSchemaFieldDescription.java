package com.cnhindustrial.telemetry.model;

public enum TelemetryStatusSchemaFieldDescription {

    TIME(TelemetryStatusFeaturePropertiesName.TIME, "Timestamp", null),
    POS_POINT(TelemetryStatusFeaturePropertiesName.POS_POINT, "Point", "srid=4326"),
    ASSET_ID(TelemetryStatusFeaturePropertiesName.ASSET_ID, "String", null),
    POS_PDOP(TelemetryStatusFeaturePropertiesName.POS_PDOP, "Integer", null),
    POS_CURRENT(TelemetryStatusFeaturePropertiesName.POS_CURRENT, "Boolean", null),
    POS_SATCOUNT(TelemetryStatusFeaturePropertiesName.POS_SATCOUNT, "Integer", null),
    POS_FIXTYPE(TelemetryStatusFeaturePropertiesName.POS_FIXTYPE, "Integer", null),
    POS_ALT(TelemetryStatusFeaturePropertiesName.POS_ALT, "Double", null),
    POS_TIME(TelemetryStatusFeaturePropertiesName.POS_TIME, "Timestamp", null),
    POS_SPEED(TelemetryStatusFeaturePropertiesName.POS_SPEED, "Double", null),
    POS_DIRECTION(TelemetryStatusFeaturePropertiesName.POS_DIRECTION, "Double", null),
    STATUS_DUTY(TelemetryStatusFeaturePropertiesName.STATUS_DUTY, "Integer", null),
    STATUS_DEVICE(TelemetryStatusFeaturePropertiesName.STATUS_DEVICE, "String", null),
    NETWORKINFO_RSSI(TelemetryStatusFeaturePropertiesName.NETWORKINFO_RSSI, "Integer", null),
    NETWORKINFO_MNC(TelemetryStatusFeaturePropertiesName.NETWORKINFO_MNC, "Integer", null),
    NETWORKINFO_NETWORK_STATUS(TelemetryStatusFeaturePropertiesName.NETWORKINFO_NETWORK_STATUS, "String", null),
    NETWORKINFO_CONNECTION(TelemetryStatusFeaturePropertiesName.NETWORKINFO_CONNECTION, "String", null),
    NETWORKINFO_MCC(TelemetryStatusFeaturePropertiesName.NETWORKINFO_MCC, "Integer", null),
    NETWORKINFO_OPERATOR_NAME(TelemetryStatusFeaturePropertiesName.NETWORKINFO_OPERATOR_NAME, "String", null),
    REPORTS(TelemetryStatusFeaturePropertiesName.REPORTS, "String", null),
    EVENTS(TelemetryStatusFeaturePropertiesName.EVENTS, "String", null),
    DEVICE_ID(TelemetryStatusFeaturePropertiesName.DEVICE_ID, "String", null),
    FROM(TelemetryStatusFeaturePropertiesName.FROM, "String", null),
    TO(TelemetryStatusFeaturePropertiesName.TO, "String", null),
    TECH_TYPE(TelemetryStatusFeaturePropertiesName.TECH_TYPE, "String", null);

    private String fieldName;
    private String type;
    private String properties;

    TelemetryStatusSchemaFieldDescription(String fieldName, String type, String properties) {
        this.fieldName = fieldName;
        this.type = type;
        this.properties = properties;
    }

    public String getDescription(){
        String basic = this.fieldName + ":" + this.type;
        if(this.properties != null){
            basic = basic + ":" + this.properties;
        }
        return basic;
    }
}
