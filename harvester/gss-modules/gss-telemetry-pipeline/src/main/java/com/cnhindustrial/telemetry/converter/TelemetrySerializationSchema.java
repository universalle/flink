package com.cnhindustrial.telemetry.converter;

import com.cnhindustrial.telemetry.common.json.ObjectMapperHelper;
import com.cnhindustrial.telemetry.common.model.TelemetryDto;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

public class TelemetrySerializationSchema implements KafkaSerializationSchema<TelemetryDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelemetrySerializationSchema.class);
    private static final long serialVersionUID = 5551627015949269834L;

    private String topic;

    public TelemetrySerializationSchema(String topic) {
        super();
        this.topic = topic;
    }

    @Override
    public ProducerRecord<byte[], byte[]> serialize(TelemetryDto telemetryDto, @Nullable Long aLong) {
        try {
            return new ProducerRecord<>(topic, ObjectMapperHelper.getObjectMapper().writeValueAsBytes(telemetryDto));
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to serialize Telemetry Object", e);
        }
        return null;
    }
}
