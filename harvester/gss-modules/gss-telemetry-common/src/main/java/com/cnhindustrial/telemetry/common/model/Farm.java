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

/**
 * Autogenerated code Controller Data transfer object. To be reviewed.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Farm implements Serializable {

    private static final long serialVersionUID = -1525369772131467795L;

    @JsonProperty("@Id")
    private String id;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Fields")
    private List<Field> fields = null;
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

    @JsonProperty("Fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("Fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
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
        return new HashCodeBuilder().append(description).append(id).append(additionalProperties).append(fields).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Farm)) {
            return false;
        }
        Farm rhs = ((Farm) other);
        return new EqualsBuilder().append(description, rhs.description).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(fields, rhs.fields).isEquals();
    }

}
