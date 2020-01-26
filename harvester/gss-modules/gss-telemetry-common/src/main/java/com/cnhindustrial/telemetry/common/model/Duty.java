package com.cnhindustrial.telemetry.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Duty implements Serializable {

    private static final long serialVersionUID = -4850249859701943607L;

    @JsonProperty("duty")
    private int dutyAmount;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("duty")
    public int getDutyAmount() {
        return dutyAmount;
    }

    @JsonProperty("duty")
    public void setDutyAmount(int dutyAmount) {
        this.dutyAmount = dutyAmount;
    }

    @JsonProperty("duration")
    public int getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(duration).append(dutyAmount).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Duty)) {
            return false;
        }
        Duty rhs = ((Duty) other);
        return new EqualsBuilder().append(duration, rhs.duration).append(dutyAmount, rhs.dutyAmount).isEquals();
    }

}
