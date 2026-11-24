package org.kampa.components;

import org.kampa.service.MqttPayload;
import org.kampa.service.MqttSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TelemetrySender {
    private final MqttSenderService mqttSenderService;
    private final MqttPayload mqttPayload;
    private static final Logger logger = LoggerFactory.getLogger(TelemetrySender.class);

    @Value("${mqtt.topic}")
    private String topic;
    public TelemetrySender(MqttSenderService mqttSenderService, MqttPayload mqttPayload) {
        this.mqttSenderService = mqttSenderService;
        this.mqttPayload = mqttPayload;
    }

    @Scheduled(fixedRateString = "${mqtt.fixed-rate}")
    public void sendPeriodicTelemetry() throws Exception {
        mqttPayload.buildInt("numero", 22222);
        mqttPayload.buildInt("numero2", 33333);
        String payload = mqttPayload.build();
        try {
            mqttSenderService.sendTelemetry(topic, payload);
            logger.info("Topic {}, payload {}", topic, payload);
        } catch (Exception e) {
            logger.error("Error occurred while sending telemetry", e);
        }
    }
}
