package com.practice.info_pilot_api.dto;

import java.util.List;

public class SemanticSearchResponse {

    private String question;

    private String generatedContext;

    private List<RankedChunkResponse> chunks;

    public SemanticSearchResponse(
            String question,
            String generatedContext,
            List<RankedChunkResponse> chunks) {

        this.question = question;
        this.generatedContext = generatedContext;
        this.chunks = chunks;
    }

    public String getQuestion() {
        return question;
    }

    public String getGeneratedContext() {
        return generatedContext;
    }

    public List<RankedChunkResponse> getChunks() {
        return chunks;
    }
}