package com.cnhindustrial.telemetry.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonAnySetter;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LargeTractorFault implements Serializable {

    private static final long serialVersionUID = -2185022179739775876L;

    @JsonProperty("ecu")
    private int ecu;
    @JsonProperty("faults")
    private List<Fault> faults = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("ecu")
    public int getEcu() {
        return ecu;
    }

    @JsonProperty("ecu")
    public void setEcu(int ecu) {
        this.ecu = ecu;
    }

    @JsonProperty("faults")
    public List<Fault> getFaults() {
        return faults;
    }

    @JsonProperty("faults")
    public void setFaults(List<Fault> faults) {
        this.faults = faults;
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
        return new HashCodeBuilder().append(additionalProperties).append(faults).append(ecu).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LargeTractorFault)) {
            return false;
        }
        LargeTractorFault rhs = ((LargeTractorFault) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(faults, rhs.faults).append(ecu, rhs.ecu).isEquals();
    }

}
