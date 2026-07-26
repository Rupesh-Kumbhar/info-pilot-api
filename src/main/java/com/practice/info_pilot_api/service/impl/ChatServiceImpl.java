package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.service.ChatService;
import com.practice.info_pilot_api.service.GeminiService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl
        implements ChatService {

    private final GeminiService geminiService;

    public ChatServiceImpl(
            GeminiService geminiService) {

        this.geminiService = geminiService;
    }

    @Override
    public String askQuestion(
            String question) {

        return geminiService
                .askQuestion(question);
    }
}