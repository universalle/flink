package com.cnhindustrial.telemetry.common.exception;

/**
 * Throw in case of missing environment variable or configuration.
 */
public class EnvironmentConfigurationException extends RuntimeException {

    public EnvironmentConfigurationException(String message) {
        super(message);
    }

    public EnvironmentConfigurationException(String message, Exception e) {
        super(message, e);
    }
}
