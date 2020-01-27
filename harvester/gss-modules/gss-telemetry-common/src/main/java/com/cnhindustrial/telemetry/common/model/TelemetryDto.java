package com.cnhindustrial.telemetry.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelemetryDto implements Serializable {

    private static final long serialVersionUID = -353712493882313441L;

    @JsonProperty("messageType")
    private String messageType;
    @JsonProperty("pos")
    private Position position;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("networkinfo")
    private NetworkInfo networkinfo;
    @JsonProperty("reports")
    private Reports reports;
    @JsonProperty("events")
    private Object events;
    @JsonProperty("time")
    private Date time;
    @JsonProperty("deviceid")
    private Long deviceid;
    @JsonProperty("assetId")
    private String assetId;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("techType")
    private String techType;
    @JsonProperty("telemetryRecords")
    private List<TelemetryRecord> telemetryRecords = new ArrayList<>();

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("messageType")
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @JsonProperty("pos")
    public Position getPosition() {
        return position;
    }

    @JsonProperty("pos")
    public void setPosition(Position position) {
        this.position = position;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("networkinfo")
    public NetworkInfo getNetworkinfo() {
        return networkinfo;
    }

    @JsonProperty("networkinfo")
    public void setNetworkinfo(NetworkInfo networkinfo) {
        this.networkinfo = networkinfo;
    }

    @JsonProperty("reports")
    public Reports getReports() {
        return reports;
    }

    @JsonProperty("reports")
    public void setReports(Reports reports) {
        this.reports = reports;
    }

    @JsonProperty("events")
    public Object getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(Object events) {
        this.events = events;
    }

    @JsonProperty("time")
    public Date getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Date time) {
        this.time = time;
    }

    @JsonProperty("deviceid")
    public Long getDeviceid() {
        return deviceid;
    }

    @JsonProperty("deviceid")
    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    @JsonProperty("assetId")
    public String getAssetId() {
        return assetId;
    }

    @JsonProperty("assetId")
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }

    @JsonProperty("techType")
    public String getTechType() {
        return techType;
    }

    @JsonProperty("techType")
    public void setTechType(String techType) {
        this.techType = techType;
    }

    public List<TelemetryRecord> getTelemetryRecords() {
        return telemetryRecords;
    }

    public void setTelemetryRecords(List<TelemetryRecord> telemetryRecords) {
        this.telemetryRecords = telemetryRecords;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(networkinfo).append(reports).append(deviceid).append(messageType).append(position).append(assetId).append(from).append(time).append(to).append(events).append(status).append(techType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TelemetryDto)) {
            return false;
        }
        TelemetryDto rhs = ((TelemetryDto) other);
        return new EqualsBuilder().append(networkinfo, rhs.networkinfo).append(reports, rhs.reports).append(deviceid, rhs.deviceid).append(messageType, rhs.messageType).append(position, rhs.position).append(assetId, rhs.assetId).append(from, rhs.from).append(time, rhs.time).append(to, rhs.to).append(events, rhs.events).append(status, rhs.status).append(techType, rhs.techType).isEquals();
    }

}
