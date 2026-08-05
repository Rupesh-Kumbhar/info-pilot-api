package com.practice.info_pilot_api.dto;

public class EmbeddingResponse {

    private String embedding;

    public EmbeddingResponse(
            String embedding) {

        this.embedding = embedding;
    }

    public String getEmbedding() {
        return embedding;
    }
}
