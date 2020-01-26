package com.cnhindustrial.telemetry.converter;

import com.cnhindustrial.telemetry.common.model.ControllerDto;

import org.apache.flink.api.common.functions.MapFunction;
import org.geotools.feature.simple.SimpleFeatureImpl;

// TODO change result type
public class GeomesaControllerFeatureConverter implements MapFunction<ControllerDto, SimpleFeatureImpl>  {

    private static final long serialVersionUID = 9037490302137843519L;

    @Override
    public SimpleFeatureImpl map(ControllerDto controllerDto) {
        //GeomesaFeature geomesaFeature = GeomesaFeature.newInstance();
        //geomesaFeature.setAttribute(FeaturePropertiesName.ASSET_ID, controllerDto.getId());
        //geomesaFeature.setAttribute(FeaturePropertiesName.TEST, controllerDto.toString());

        // TODO set real controller data

        //return geomesaFeature;
        return null;
    }
}
