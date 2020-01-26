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
import java.util.Map;

/**
 * Autogenerated code Controller Data transfer object. To be reviewed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ControllerDto implements Serializable {

    private static final long serialVersionUID = -4513761093500671849L;

    @JsonProperty("@Id")
    private String id;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Summary")
    private Summary summary;
    @JsonProperty("LoggedData")
    private LoggedData loggedData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("@Id")
    public String getId() {
        return id;
    }

    @JsonProperty("@Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Summary")
    public Summary getSummary() {
        return summary;
    }

    @JsonProperty("Summary")
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @JsonProperty("LoggedData")
    public LoggedData getLoggedData() {
        return loggedData;
    }

    @JsonProperty("LoggedData")
    public void setLoggedData(LoggedData loggedData) {
        this.loggedData = loggedData;
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
        return new HashCodeBuilder().append(summary).append(description).append(loggedData).append(id).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ControllerDto)) {
            return false;
        }
        ControllerDto rhs = ((ControllerDto) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(description, rhs.description).append(loggedData, rhs.loggedData).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    @Override
    public String toString() {
        return "ControllerDto{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}