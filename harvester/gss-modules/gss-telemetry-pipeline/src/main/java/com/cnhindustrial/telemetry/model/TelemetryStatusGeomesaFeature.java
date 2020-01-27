package com.cnhindustrial.telemetry.model;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;

import java.io.Serializable;

public class TelemetryStatusGeomesaFeature extends SimpleFeatureImpl implements Serializable {

    private static FilterFactory2 FILTER_FACTORY = CommonFactoryFinder.getFilterFactory2(null);
    private static final boolean DEFAULT_VALIDATION_STATE = false;

    private TelemetryStatusGeomesaFeature(Object[] values, SimpleFeatureType featureType, FeatureId id, boolean validating) {
        super(values, featureType, id, validating);
    }

    public static TelemetryStatusGeomesaFeature newInstance() {
        SimpleFeatureType featureType = TelemetryStatusSimpleFeatureTypeBuilder.getFeatureType();
        return new TelemetryStatusGeomesaFeature(
                new Object[featureType.getAttributeCount()],
                featureType,
                FILTER_FACTORY.featureId(SimpleFeatureBuilder.createDefaultFeatureId()),
                DEFAULT_VALIDATION_STATE);
    }
}
