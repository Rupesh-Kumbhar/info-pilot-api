package com.practice.info_pilot_api.dto;

import java.time.LocalDateTime;

public class ChatHistoryResponse {

    private Long id;
    private String question;
    private String answer;
    private String sourceDocument;
    private LocalDateTime askedAt;

    public ChatHistoryResponse(
            Long id,
            String question,
            String answer,
            String sourceDocument,
            LocalDateTime askedAt) {

        this.id = id;
        this.question = question;
        this.answer = answer;
        this.sourceDocument = sourceDocument;
        this.askedAt = askedAt;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSourceDocument() {
        return sourceDocument;
    }

    public LocalDateTime getAskedAt() {
        return askedAt;
    }
}