package com.cnhindustrial.telemetry.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fault implements Serializable {

    private static final long serialVersionUID = -1434267305633056049L;

    @JsonProperty("errorCode")
    private int errorCode;
    @JsonProperty("errorTime")
    private int errorTime;
    @JsonProperty("errorOccurenceCount")
    private int errorOccurenceCount;
    @JsonProperty("errorStatus")
    private int errorStatus;
    @JsonProperty("requestAcknowledgment")
    private int requestAcknowledgment;
    @JsonProperty("errorTimeType")
    private int errorTimeType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("errorCode")
    public int getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("errorTime")
    public int getErrorTime() {
        return errorTime;
    }

    @JsonProperty("errorTime")
    public void setErrorTime(int errorTime) {
        this.errorTime = errorTime;
    }

    @JsonProperty("errorOccurenceCount")
    public int getErrorOccurenceCount() {
        return errorOccurenceCount;
    }

    @JsonProperty("errorOccurenceCount")
    public void setErrorOccurenceCount(int errorOccurenceCount) {
        this.errorOccurenceCount = errorOccurenceCount;
    }

    @JsonProperty("errorStatus")
    public int getErrorStatus() {
        return errorStatus;
    }

    @JsonProperty("errorStatus")
    public void setErrorStatus(int errorStatus) {
        this.errorStatus = errorStatus;
    }

    @JsonProperty("requestAcknowledgment")
    public int getRequestAcknowledgment() {
        return requestAcknowledgment;
    }

    @JsonProperty("requestAcknowledgment")
    public void setRequestAcknowledgment(int requestAcknowledgment) {
        this.requestAcknowledgment = requestAcknowledgment;
    }

    @JsonProperty("errorTimeType")
    public int getErrorTimeType() {
        return errorTimeType;
    }

    @JsonProperty("errorTimeType")
    public void setErrorTimeType(int errorTimeType) {
        this.errorTimeType = errorTimeType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(errorTime).append(errorTimeType).append(errorCode).append(errorStatus).append(requestAcknowledgment).append(errorOccurenceCount).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Fault) == false) {
            return false;
        }
        Fault rhs = ((Fault) other);
        return new EqualsBuilder().append(errorTime, rhs.errorTime).append(errorTimeType, rhs.errorTimeType).append(errorCode, rhs.errorCode).append(errorStatus, rhs.errorStatus).append(requestAcknowledgment, rhs.requestAcknowledgment).append(errorOccurenceCount, rhs.errorOccurenceCount).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
