package com.kampa.telemetry.components;

import com.kampa.telemetry.service.MqttSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TelemetrySender {
    private final MqttSenderService mqttSenderService;
    private static final Logger logger = LoggerFactory.getLogger(TelemetrySender.class);

    public TelemetrySender(MqttSenderService mqttSenderService) {
        this.mqttSenderService = mqttSenderService;
    }

    @Scheduled(fixedRate = 5000)
    public void sendPeriodicTelemetry() {
        String topic = "telemetria/temperatura";
        String payload = "{\"temperature\": 25.5}";
        try {
            mqttSenderService.sendTelemetry(topic, payload);
            logger.info("Telemetria inviata");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
