package com.kampa.telemetry.components;

import com.kampa.telemetry.service.MqttPayload;
import com.kampa.telemetry.service.MqttSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TelemetrySender {
    private final MqttSenderService mqttSenderService;
    private final MqttPayload mqttPayload;
    private static final Logger logger = LoggerFactory.getLogger(TelemetrySender.class);

    public TelemetrySender(MqttSenderService mqttSenderService, MqttPayload mqttPayolad) {
        this.mqttSenderService = mqttSenderService;
        this.mqttPayload = mqttPayolad;
    }

    @Scheduled(fixedRateString = "${mqtt.fixed-rate}")
    public void sendPeriodicTelemetry() {
        String topic = "telemetria/temperatura";
        mqttPayload.buildInt("numero", 22222);
        mqttPayload.buildInt("numero2", 33333);
        String payload = mqttPayload.build();
        try {
            mqttSenderService.sendTelemetry(topic, payload);
            logger.info("Topic {}, payload {}", topic, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
