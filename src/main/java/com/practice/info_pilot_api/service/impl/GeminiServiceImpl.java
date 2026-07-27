package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.config.GeminiConfig;
import com.practice.info_pilot_api.dto.GeminiRequest;
import com.practice.info_pilot_api.dto.GeminiResponse;
import com.practice.info_pilot_api.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeminiServiceImpl
        implements GeminiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GeminiConfig geminiConfig;

    @Override
    public String askQuestion(String question) {

        try {

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent?key="
                            + geminiConfig.getApiKey();

            GeminiRequest.Part part =
                    new GeminiRequest.Part(question);

            GeminiRequest.Content content =
                    new GeminiRequest.Content(
                            List.of(part));

            GeminiRequest request =
                    new GeminiRequest(
                            List.of(content));

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON);

            HttpEntity<GeminiRequest> entity =
                    new HttpEntity<>(
                            request,
                            headers);

            ResponseEntity<GeminiResponse>
                    response =
                    restTemplate.postForEntity(
                            url,
                            entity,
                            GeminiResponse.class);

            GeminiResponse body = response.getBody();

            if (body == null
                    || body.getCandidates() == null
                    || body.getCandidates().isEmpty()) {

                return "No response from Gemini";
            }

            return body.getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

        } catch (Exception ex) {

            ex.printStackTrace();

            return "Gemini Error : "
                    + ex.getMessage();
        }
    }
}