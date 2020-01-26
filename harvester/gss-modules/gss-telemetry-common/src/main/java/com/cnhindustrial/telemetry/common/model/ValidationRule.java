package com.cnhindustrial.telemetry.common.model;

public interface ValidationRule <T> {
    boolean validate(T value);
}
