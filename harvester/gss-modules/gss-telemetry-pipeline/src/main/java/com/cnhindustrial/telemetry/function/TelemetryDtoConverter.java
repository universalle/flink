package com.cnhindustrial.telemetry.function;

import com.cnhindustrial.telemetry.common.model.TelemetryDto;

import java.io.Serializable;
import java.util.function.Function;

public class TelemetryDtoConverter implements Function<TelemetryDto, TelemetryDto>, Serializable {

    private static final long serialVersionUID = -6543512445747218321L;

    @Override
    public TelemetryDto apply(TelemetryDto telemetryDto) {

        // TODO add some conversion

        return new TelemetryDto();
    }
}
