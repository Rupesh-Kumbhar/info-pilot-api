package com.practice.info_pilot_api.service;

import com.practice.info_pilot_api.dto.ChatHistoryResponse;
import com.practice.info_pilot_api.dto.ChatResponse;

import java.util.List;

public interface ChatService {

    ChatResponse askQuestion(
            String question,
            Long documentId);

    List<ChatHistoryResponse> getHistory();

    long getTotalQuestions();
}