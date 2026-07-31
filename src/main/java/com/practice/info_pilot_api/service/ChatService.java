package com.practice.info_pilot_api.service;

import com.practice.info_pilot_api.dto.ChatHistoryResponse;
import java.util.List;

public interface ChatService {

    String askQuestion(String question);

    List<ChatHistoryResponse> getHistory();
}