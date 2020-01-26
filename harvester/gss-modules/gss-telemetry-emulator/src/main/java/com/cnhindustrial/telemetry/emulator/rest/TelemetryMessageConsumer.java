package com.cnhindustrial.telemetry.emulator.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
@Scope("prototype")
public class TelemetryMessageConsumer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TelemetryMessageConsumer.class);
    private final BlockingQueue<String> queue;
    private final KafkaProducer kafkaProducer;

    TelemetryMessageConsumer(BlockingQueue<String> queue, KafkaProducer kafkaProducer) {
        this.queue = queue;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume(queue.take());
            } catch (InterruptedException e) {
                logger.error("Failed to consume message from queue");
            }
        }
    }

    void consume(String message) {
        kafkaProducer.sendMessage(message);
    }
}
