package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.service.EmbeddingService;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingServiceImpl
                implements EmbeddingService {

        @Override
        public String generateEmbedding(
                        String text) {

                int hash = Math.abs(
                                text.hashCode());

                double v1 = (hash % 1000) / 1000.0;

                double v2 = ((hash / 1000) % 1000)
                                / 1000.0;

                double v3 = ((hash / 1000000) % 1000)
                                / 1000.0;

                return v1 +
                                "," +
                                v2 +
                                "," +
                                v3;
        }
}