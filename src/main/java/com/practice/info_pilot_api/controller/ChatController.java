package com.practice.info_pilot_api.controller;

import com.practice.info_pilot_api.dto.ChatHistoryResponse;
import com.practice.info_pilot_api.dto.ChatRequest;
import com.practice.info_pilot_api.dto.ChatResponse;
import com.practice.info_pilot_api.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

        private final ChatService chatService;

        public ChatController(
                        ChatService chatService) {

                this.chatService = chatService;
        }

        @PostMapping("/ask")
        public ResponseEntity<ChatResponse> askQuestion(
                        @RequestBody ChatRequest request) {

                ChatResponse response = chatService.askQuestion(
                                request.getQuestion(),
                                request.getDocumentId());

                return ResponseEntity.ok(
                                response);
        }

        @GetMapping("/history")
        public List<ChatHistoryResponse> getHistory() {

                return chatService
                                .getHistory();
        }
}