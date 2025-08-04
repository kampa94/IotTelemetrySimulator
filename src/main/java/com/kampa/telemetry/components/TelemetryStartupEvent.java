package com.kampa.telemetry.components;
import com.kampa.telemetry.service.MqttSenderService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TelemetryStartupEvent {

    private final MqttSenderService mqttSenderService;

    public TelemetryStartupEvent(MqttSenderService mqttSenderService) {
        this.mqttSenderService = mqttSenderService;
    }

    @EventListener
    public void onStartup(ContextRefreshedEvent event) {
        String topic = "telemetria/temperatura";
        String payload = "{\"temperature\": 25.5}";
        try {
            mqttSenderService.sendTelemetry(topic, payload);
            System.out.println("Telemetria inviata al broker MQTT!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
