package com.practice.info_pilot_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.info_pilot_api.config.GeminiConfig;

import java.util.Map;

/*
POST /documents/upload
POST /chat/ask
 */

@RestController
public class HealthController {
    @Autowired GeminiConfig geminiConfig;

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "application", "Info Pilot API"
        );
    }

    @GetMapping("/key-check")
    public String keyCheck() {
        return geminiConfig.getApiKey();
    }
}