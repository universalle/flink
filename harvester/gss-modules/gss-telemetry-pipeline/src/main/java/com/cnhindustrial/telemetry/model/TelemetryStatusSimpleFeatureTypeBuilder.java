package com.cnhindustrial.telemetry.model;

import org.locationtech.geomesa.utils.interop.SimpleFeatureTypes;
import org.opengis.feature.simple.SimpleFeatureType;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TelemetryStatusSimpleFeatureTypeBuilder {
    private static final String SCHEMA;
    private static final SimpleFeatureType DEFAULT_FEATURE_TYPE;
    private static final String FEATURE_NAME;
    private static final String PROPERTIES_DELIMITER;
    static {
        FEATURE_NAME = "telemetry_status";
        PROPERTIES_DELIMITER = ",";
        SCHEMA = Stream.of(TelemetryStatusSchemaFieldDescription.values())
                .map(TelemetryStatusSchemaFieldDescription::getDescription)
                .collect(Collectors.joining(PROPERTIES_DELIMITER));
        DEFAULT_FEATURE_TYPE = SimpleFeatureTypes.createType(FEATURE_NAME, SCHEMA);
    }

    public static SimpleFeatureType getFeatureType(){
        return DEFAULT_FEATURE_TYPE;
    }
}
