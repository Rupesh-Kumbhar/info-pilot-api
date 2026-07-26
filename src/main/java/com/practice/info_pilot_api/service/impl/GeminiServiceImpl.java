package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.service.GeminiService;
import org.springframework.stereotype.Service;

@Service
public class GeminiServiceImpl
        implements GeminiService {

    @Override
    public String askQuestion(String question) {

        return "Received Question : " + question;
    }
}