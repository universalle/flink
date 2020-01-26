package com.cnhindustrial.telemetry.emulator.rest;

import com.cnhindustrial.telemetry.common.model.TelemetryDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

//    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(TelemetryDto message) {
        logger.info("#### -> Consumed message -> {}", message);
    }
}
