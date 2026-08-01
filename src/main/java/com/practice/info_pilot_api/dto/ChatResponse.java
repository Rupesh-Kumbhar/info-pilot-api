package com.practice.info_pilot_api.dto;

public class ChatResponse {

    private String answer;

    private String sourceDocument;

    public ChatResponse (String answer, String sourceDocument) {
        this.answer = answer;
        this.sourceDocument = sourceDocument;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSourceDocument() {
        return sourceDocument;
    }
}