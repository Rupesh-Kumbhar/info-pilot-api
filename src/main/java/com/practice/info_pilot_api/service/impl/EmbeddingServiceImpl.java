package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.service.EmbeddingService;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingServiceImpl
        implements EmbeddingService {

    @Override
    public String generateEmbedding(
            String text) {

        /*
         Temporary placeholder.

         Next sprint:
         Call Gemini Embedding API.

         Example:
         [0.241,0.878,0.123...]
        */

        return String.valueOf(
                text.hashCode()
        );
    }
}