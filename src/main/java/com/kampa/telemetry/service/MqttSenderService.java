package com.kampa.telemetry.service;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttSenderService {

    private IMqttClient client;
    private final String MQTT_SERVER_URI = "tcp://localhost:1883"; // Cambia con il tuo broker!
    private final String CLIENT_ID = "spring-telemetry-sender";

    @PostConstruct
    public void init() throws Exception {
        client = new MqttClient(MQTT_SERVER_URI, CLIENT_ID);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        client.connect(options);
    }

    public void sendTelemetry(String topic, String payload) throws Exception {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(0);
        message.setRetained(false);
        client.publish(topic, message);
    }

}