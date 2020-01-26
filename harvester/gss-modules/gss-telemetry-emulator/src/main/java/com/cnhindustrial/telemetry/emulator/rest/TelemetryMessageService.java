package com.cnhindustrial.telemetry.emulator.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TelemetryMessageService {
    private static final Logger logger = LoggerFactory.getLogger(TelemetryMessageService.class);

    private int maxNumberOfMessages = 0;
    private AtomicInteger counter = new AtomicInteger(0);

    private final BlockingQueue<String> queue;

    TelemetryMessageService(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public int getMaxNumberOfMessages() {
        return maxNumberOfMessages;
    }

    public void setMaxNumberOfMessages(int maxNumberOfMessages) {
        logger.info("Setting max number of messages per second to {}.", maxNumberOfMessages);
        this.maxNumberOfMessages = maxNumberOfMessages;
    }

    public int getAndIncrementCounter() {
        return counter.getAndUpdate(value -> value > maxNumberOfMessages ? value : ++value);
    }

    @Scheduled(fixedRate = 1000)
    public void resetCounter() {
//        logger.info("Messages in queue: " + queue.size());
        counter.set(0);
    }
}
