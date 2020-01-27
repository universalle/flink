package com.cnhindustrial.telemetry.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelemetryRecord implements Serializable {

    private static final long serialVersionUID = -4387294490319968863L;

    @JsonProperty("messageType")
    private String messageType;
    @JsonProperty("id")
    private int id;
    @JsonProperty("offset")
    private int offset;
    @JsonProperty("resolution")
    private int resolution;
    @JsonProperty("familyCode")
    private String familyCode;
    @JsonProperty("parameterType")
    private String parameterType;
    @JsonProperty("max")
    private double max;
    @JsonProperty("min")
    private double min;
    @JsonProperty("cnt")
    private double cnt;
    @JsonProperty("sum")
    private double sum;
    @JsonProperty("reportType")
    private String reportType;
    @JsonProperty("samplingPeriod")
    private int samplingPeriod;
    @JsonProperty("issigned")
    private boolean issigned;
    @JsonProperty("confidential")
    private boolean confidential;
    @JsonProperty("valueType")
    private String valueType;
    @JsonProperty("last")
    private double last;
    @JsonProperty("translatedValue")
    private double translatedValue;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("unitCode")
    private String unitCode;
    @JsonProperty("decodedValue")
    private String decodedValue;
    @JsonProperty("time")
    private Date time;
    @JsonProperty("deviceid")
    private Long deviceId;
    @JsonProperty("assetId")
    private String assetId;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("techType")
    private Long techType;
    @JsonProperty("pos")
    private Position position;
    @JsonProperty("line")
    private int line;

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("messageType")
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("offset")
    public int getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @JsonProperty("resolution")
    public int getResolution() {
        return resolution;
    }

    @JsonProperty("resolution")
    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @JsonProperty("familyCode")
    public String getFamilyCode() {
        return familyCode;
    }

    @JsonProperty("familyCode")
    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    @JsonProperty("parameterType")
    public String getParameterType() {
        return parameterType;
    }

    @JsonProperty("parameterType")
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    @JsonProperty("reportType")
    public String getReportType() {
        return reportType;
    }

    @JsonProperty("reportType")
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    @JsonProperty("samplingPeriod")
    public int getSamplingPeriod() {
        return samplingPeriod;
    }

    @JsonProperty("samplingPeriod")
    public void setSamplingPeriod(int samplingPeriod) {
        this.samplingPeriod = samplingPeriod;
    }

    @JsonProperty("issigned")
    public boolean isIssigned() {
        return issigned;
    }

    @JsonProperty("issigned")
    public void setIssigned(boolean issigned) {
        this.issigned = issigned;
    }

    @JsonProperty("confidential")
    public boolean isConfidential() {
        return confidential;
    }

    @JsonProperty("confidential")
    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    @JsonProperty("valueType")
    public String getValueType() {
        return valueType;
    }

    @JsonProperty("valueType")
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @JsonProperty("last")
    public double getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setLast(double last) {
        this.last = last;
    }

    @JsonProperty("translatedValue")
    public double getTranslatedValue() {
        return translatedValue;
    }

    @JsonProperty("translatedValue")
    public void setTranslatedValue(double translatedValue) {
        this.translatedValue = translatedValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getCnt() {
        return cnt;
    }

    public void setCnt(double cnt) {
        this.cnt = cnt;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDecodedValue() {
        return decodedValue;
    }

    public void setDecodedValue(String decodedValue) {
        this.decodedValue = decodedValue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getTechType() {
        return techType;
    }

    public void setTechType(Long techType) {
        this.techType = techType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(parameterType).append(offset).append(last).append(familyCode).append(translatedValue).append(resolution).append(reportType).append(messageType).append(valueType).append(samplingPeriod).append(id).append(issigned).append(confidential).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TelemetryRecord)) {
            return false;
        }
        TelemetryRecord rhs = ((TelemetryRecord) other);
        return new EqualsBuilder().append(parameterType, rhs.parameterType).append(offset, rhs.offset).append(last, rhs.last).append(familyCode, rhs.familyCode).append(translatedValue, rhs.translatedValue).append(resolution, rhs.resolution).append(reportType, rhs.reportType).append(messageType, rhs.messageType).append(valueType, rhs.valueType).append(samplingPeriod, rhs.samplingPeriod).append(id, rhs.id).append(issigned, rhs.issigned).append(confidential, rhs.confidential).isEquals();
    }

}
