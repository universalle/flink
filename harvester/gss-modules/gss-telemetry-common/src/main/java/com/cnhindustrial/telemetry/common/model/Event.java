package com.cnhindustrial.telemetry.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event implements Serializable {

    private static final long serialVersionUID = -7322967690590519390L;

    @JsonProperty("largeTractorFaults")
    private List<LargeTractorFault> largeTractorFaults = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("largeTractorFaults")
    public List<LargeTractorFault> getLargeTractorFaults() {
        return largeTractorFaults;
    }

    @JsonProperty("largeTractorFaults")
    public void setLargeTractorFaults(List<LargeTractorFault> largeTractorFaults) {
        this.largeTractorFaults = largeTractorFaults;
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
        return new HashCodeBuilder().append(largeTractorFaults).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Event)) {
            return false;
        }
        Event rhs = ((Event) other);
        return new EqualsBuilder().append(largeTractorFaults, rhs.largeTractorFaults).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
