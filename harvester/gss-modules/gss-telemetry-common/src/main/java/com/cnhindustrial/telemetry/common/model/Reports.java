package com.cnhindustrial.telemetry.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reports implements Serializable {

    private static final long serialVersionUID = 4685538944665852191L;

    @JsonProperty("dutyReports")
    private List<DutyReport> dutyReports = null;

    @JsonProperty("dutyReports")
    public List<DutyReport> getDutyReports() {
        return dutyReports;
    }

    @JsonProperty("dutyReports")
    public void setDutyReports(List<DutyReport> dutyReports) {
        this.dutyReports = dutyReports;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dutyReports).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Reports)) {
            return false;
        }
        Reports rhs = ((Reports) other);
        return new EqualsBuilder().append(dutyReports, rhs.dutyReports).isEquals();
    }

}
