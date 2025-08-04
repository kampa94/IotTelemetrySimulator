package com.kampa.telemetry.components;
import com.kampa.telemetry.service.MqttSenderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TelemetryStartupRunner implements CommandLineRunner {

    private final MqttSenderService mqttSenderService;

    public TelemetryStartupRunner(MqttSenderService mqttSenderService) {
        this.mqttSenderService = mqttSenderService;
    }

    @Override
    public void run(String... args) throws Exception {
        String topic = "telemetria/temperatura";
        String payload = "{\"temperature\": 25.5}";
        mqttSenderService.sendTelemetry(topic, payload);
        System.out.println("Telemetria inviata al broker MQTT!");
    }
}
