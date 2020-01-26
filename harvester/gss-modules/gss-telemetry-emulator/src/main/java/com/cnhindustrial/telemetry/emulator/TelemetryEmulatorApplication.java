package com.cnhindustrial.telemetry.emulator;

import com.cnhindustrial.telemetry.common.model.TelemetryDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.LinkedBlockingQueue;

@EnableScheduling
@SpringBootApplication
public class TelemetryEmulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelemetryEmulatorApplication.class, args);
    }

    @Bean
    public LinkedBlockingQueue<String> queue() {
        return new LinkedBlockingQueue<>();
    }
}
