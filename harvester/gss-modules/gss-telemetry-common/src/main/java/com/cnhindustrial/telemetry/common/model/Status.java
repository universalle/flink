package com.cnhindustrial.telemetry.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status implements Serializable {

    private static final long serialVersionUID = 1564874662677208783L;

    @JsonProperty("device")
    private String device;
    @JsonProperty("duty")
    private int duty;

    @JsonProperty("device")
    public String getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(String device) {
        this.device = device;
    }

    @JsonProperty("duty")
    public int getDuty() {
        return duty;
    }

    @JsonProperty("duty")
    public void setDuty(int duty) {
        this.duty = duty;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(device).append(duty).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Status)) {
            return false;
        }
        Status rhs = ((Status) other);
        return new EqualsBuilder().append(device, rhs.device).append(duty, rhs.duty).isEquals();
    }

}
