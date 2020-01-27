package com.cnhindustrial.telemetry.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NetworkInfo implements Serializable {

    private static final long serialVersionUID = 5076661872706723790L;

    private int rssi;
    @JsonProperty("MNC")
    private int mnc;
    @JsonProperty("NetworkStatus")
    private String networkStatus;
    private String connection;
    @JsonProperty("MCC")
    private int mcc;
    private String operatorName;

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "NetworkInfo{" +
                "rssi=" + rssi +
                ", MNC=" + mnc +
                ", NetworkStatus='" + networkStatus + '\'' +
                ", connection='" + connection + '\'' +
                ", MCC=" + mcc +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
