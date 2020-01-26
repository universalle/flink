package com.cnhindustrial.telemetry.function;

import com.cnhindustrial.telemetry.common.model.TelemetryValidationRules;
import com.cnhindustrial.telemetry.common.model.TelemetryDto;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelemetryValidationFunction extends ProcessFunction<TelemetryDto, TelemetryDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetryValidationFunction.class);
    private static final long serialVersionUID = -5985712453775192298L;

    private final OutputTag<TelemetryDto> outputTag;

    public TelemetryValidationFunction(OutputTag<TelemetryDto> outputTag) {
        this.outputTag = outputTag;
    }


    @Override
    public void processElement(TelemetryDto value, Context context, Collector<TelemetryDto> collector) throws Exception {
        if (passedValidation(value)) {
            // emit data to regular output
            collector.collect(value);
        } else {
            // emit data to side output
            context.output(outputTag, value);
        }
    }

    private boolean passedValidation(TelemetryDto value) {
        for (TelemetryValidationRules validationRule : TelemetryValidationRules.values()) {
            if (!validationRule.validate(value)) {
                return false;
            }
        }

        return true;
    }
}
