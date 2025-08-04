package com.kampa.telemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IotTelemetrySimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotTelemetrySimulatorApplication.class, args);
    }

}
