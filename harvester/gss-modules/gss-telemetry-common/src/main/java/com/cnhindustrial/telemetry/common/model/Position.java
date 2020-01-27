package com.cnhindustrial.telemetry.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Position implements Serializable {

    private static final long serialVersionUID = 1089419898767138854L;

    @JsonProperty("time")
    private Date time;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;
    @JsonProperty("alt")
    private double alt;
    @JsonProperty("direction")
    private double direction;
    @JsonProperty("speed")
    private int speed;
    @JsonProperty("fixtype")
    private int fixtype;
    @JsonProperty("pdop")
    private double pdop;
    @JsonProperty("satcount")
    private int satcount;
    @JsonProperty("current")
    private boolean current;

    @JsonProperty("time")
    public Date getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Date time) {
        this.time = time;
    }

    @JsonProperty("lat")
    public double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(double lat) {
        this.lat = lat;
    }

    @JsonProperty("lon")
    public double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(double lon) {
        this.lon = lon;
    }

    @JsonProperty("alt")
    public double getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(double alt) {
        this.alt = alt;
    }

    @JsonProperty("direction")
    public double getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(double direction) {
        this.direction = direction;
    }

    @JsonProperty("speed")
    public int getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @JsonProperty("fixtype")
    public int getFixtype() {
        return fixtype;
    }

    @JsonProperty("fixtype")
    public void setFixtype(int fixtype) {
        this.fixtype = fixtype;
    }

    @JsonProperty("pdop")
    public double getPdop() {
        return pdop;
    }

    @JsonProperty("pdop")
    public void setPdop(double pdop) {
        this.pdop = pdop;
    }

    @JsonProperty("satcount")
    public int getSatcount() {
        return satcount;
    }

    @JsonProperty("satcount")
    public void setSatcount(int satcount) {
        this.satcount = satcount;
    }

    @JsonProperty("current")
    public boolean isCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(boolean current) {
        this.current = current;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pdop).append(current).append(satcount).append(fixtype).append(alt).append(lon).append(time).append(lat).append(speed).append(direction).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Position)) {
            return false;
        }
        Position rhs = ((Position) other);
        return new EqualsBuilder().append(pdop, rhs.pdop).append(current, rhs.current).append(satcount, rhs.satcount).append(fixtype, rhs.fixtype).append(alt, rhs.alt).append(lon, rhs.lon).append(time, rhs.time).append(lat, rhs.lat).append(speed, rhs.speed).append(direction, rhs.direction).isEquals();
    }

}
