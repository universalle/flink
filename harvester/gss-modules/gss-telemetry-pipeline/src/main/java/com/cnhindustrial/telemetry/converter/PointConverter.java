package com.cnhindustrial.telemetry.converter;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.Serializable;

public class PointConverter implements Serializable {
    private static final int SPATIAL_PROJECTION_4328 = 4328;
    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), SPATIAL_PROJECTION_4328);

    public Point convert(Double lat, Double lon){
        Coordinate coordinate = new Coordinate(lon, lat);
        return GEOMETRY_FACTORY.createPoint(coordinate);
    }
}
