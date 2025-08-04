package com.kampa.telemetry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MqttPayload {
    private final Map<String, Object> payolad = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(MqttPayload.class);

    public void buildInt(String key, int value) {
        logger.info(payolad.toString());
        payolad.put(key, value);

    }

    public String build() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(payolad);
        } catch (JsonProcessingException e) {
            throw new Error(e);
        }
    }
}
