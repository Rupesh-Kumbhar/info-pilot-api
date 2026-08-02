package com.practice.info_pilot_api.dto;

public class ChatRequest {

    private String question;

    private Long documentId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(
            String question) {
        this.question = question;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(
            Long documentId) {
        this.documentId = documentId;
    }
}