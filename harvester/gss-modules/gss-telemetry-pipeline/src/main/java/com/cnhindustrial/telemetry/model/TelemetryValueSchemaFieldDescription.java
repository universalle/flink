package com.cnhindustrial.telemetry.model;

public enum TelemetryValueSchemaFieldDescription {
    TIME(TelemetryValueFeaturePropertiesName.TIME, "Timestamp", null),
    POS_POINT(TelemetryValueFeaturePropertiesName.POS_POINT, "Point", "srid=4326"),
    ASSET_ID(TelemetryValueFeaturePropertiesName.ASSET_ID, "String", null),
    FAMILY_CODE(TelemetryValueFeaturePropertiesName.FAMILY_CODE, "String", null),
    ID(TelemetryValueFeaturePropertiesName.ID, "Integer", null),
    DEVICE_ID(TelemetryValueFeaturePropertiesName.DEVICE_ID, "String", null),
    CONFIDENTIAL(TelemetryValueFeaturePropertiesName.CONFIDENTIAL, "Boolean", null),
    TRANSLATED_VALUE(TelemetryValueFeaturePropertiesName.TRANSLATED_VALUE, "Double", null),
    OFFSET(TelemetryValueFeaturePropertiesName.OFFSET, "Double", null),
    SAMPLING_PERIOD(TelemetryValueFeaturePropertiesName.SAMPLING_PERIOD, "Integer", null),
    VALUE_TYPE(TelemetryValueFeaturePropertiesName.VALUE_TYPE, "String", null),
    REPORT_TYPE(TelemetryValueFeaturePropertiesName.REPORT_TYPE, "String", null),
    RESOLUTION(TelemetryValueFeaturePropertiesName.RESOLUTION, "Double", null),
    PARAMETER_TYPE(TelemetryValueFeaturePropertiesName.PARAMETER_TYPE, "String", null),
    TECH_TYPE(TelemetryValueFeaturePropertiesName.TECH_TYPE, "String", null),
    IS_SIGNED(TelemetryValueFeaturePropertiesName.IS_SIGNED, "String", null),
    TO(TelemetryValueFeaturePropertiesName.TO, "String", null),
    FROM(TelemetryValueFeaturePropertiesName.FROM, "String", null),
    DECODED_VALUE(TelemetryValueFeaturePropertiesName.DECODED_VALUE, "String", null),
    LAST(TelemetryValueFeaturePropertiesName.LAST, "Double", null),
    MIN(TelemetryValueFeaturePropertiesName.MIN, "Double", null),
    MAX(TelemetryValueFeaturePropertiesName.MAX, "Double", null),
    SUM(TelemetryValueFeaturePropertiesName.SUM, "Double", null),
    CNT(TelemetryValueFeaturePropertiesName.CNT, "Integer", null),
    LINE(TelemetryValueFeaturePropertiesName.LINE, "Integer", null);

    private String fieldName;
    private String type;
    private String properties;

    TelemetryValueSchemaFieldDescription(String fieldName, String type, String properties) {
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
