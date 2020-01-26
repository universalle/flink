package com.cnhindustrial.telemetry.model;

import java.util.ArrayList;
import java.util.List;

public class TelemetryFeatureWrapper {
    private TelemetryStatusGeomesaFeature statusFeature;
    private List<TelemetryValueGeomesaFeature> valueFeatures = new ArrayList<>();

    public TelemetryStatusGeomesaFeature getStatusFeature() {
        return statusFeature;
    }

    public void setStatusFeature(TelemetryStatusGeomesaFeature statusFeature) {
        this.statusFeature = statusFeature;
    }

    public List<TelemetryValueGeomesaFeature> getValueFeatures() {
        return valueFeatures;
    }

    public void addValueFeature(TelemetryValueGeomesaFeature valueFeature) {
        this.valueFeatures.add(valueFeature);
    }
}
