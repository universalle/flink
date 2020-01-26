package com.cnhindustrial.telemetry.emulator.rest;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RestController
public class EmulatorController {
    private static final Logger logger = LoggerFactory.getLogger(EmulatorController.class);
    private static final int producersNumber = 5;
    private static final int consumersNumber = 50;

    ExecutorService producerExecutor = Executors.newFixedThreadPool(producersNumber);
    ExecutorService consumerExecutor = Executors.newFixedThreadPool(consumersNumber);

    private final TelemetryMessageService messageService;

    public EmulatorController(BeanFactory beanFactory, TelemetryMessageService messageService) {
        this.messageService = messageService;

        IntStream.range(0, producersNumber).forEach(value ->
                producerExecutor.submit(beanFactory.getBean(TelemetryMessageProducer.class))
        );
        IntStream.range(0, consumersNumber).forEach(value ->
                consumerExecutor.submit(beanFactory.getBean(TelemetryMessageConsumer.class))
        );
    }

    @PostMapping("api/v1/emulate")
    @ApiOperation(value = "Emulate telemetry data and send it somewhere")
    public void emulate(int number) {
        messageService.setMaxNumberOfMessages(number);
    }
}
