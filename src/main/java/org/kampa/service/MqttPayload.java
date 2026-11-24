package org.kampa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Component
public class MqttPayload {
    private final Map<String, Object> payload = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(MqttPayload.class);

    public void buildInt(String key, int value) {
        payload.put(key, value);
        logger.info(payload.toString());

    }

    public String build() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (Error e) {
            throw new Exception("Error occurred while building MQTT payload", e);
        }
    }
}
