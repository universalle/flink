
package com.cnhindustrial.telemetry.common.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DutyReport implements Serializable {

    private static final long serialVersionUID = -5284982637090374520L;

    @JsonProperty("version")
    private int version;
    @JsonProperty("reportid")
    private int reportid;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("duties")
    private List<Duty> duties = null;

    @JsonProperty("version")
    public int getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(int version) {
        this.version = version;
    }

    @JsonProperty("reportid")
    public int getReportid() {
        return reportid;
    }

    @JsonProperty("reportid")
    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    @JsonProperty("duration")
    public int getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty("duties")
    public List<Duty> getDuties() {
        return duties;
    }

    @JsonProperty("duties")
    public void setDuties(List<Duty> duties) {
        this.duties = duties;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(duration).append(reportid).append(version).append(duties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DutyReport)) {
            return false;
        }
        DutyReport rhs = ((DutyReport) other);
        return new EqualsBuilder().append(duration, rhs.duration).append(reportid, rhs.reportid).append(version, rhs.version).append(duties, rhs.duties).isEquals();
    }

}
