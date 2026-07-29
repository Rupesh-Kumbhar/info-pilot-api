package com.practice.info_pilot_api.controller;

import com.practice.info_pilot_api.dto.ChatRequest;
import com.practice.info_pilot_api.dto.ChatResponse;
import com.practice.info_pilot_api.service.ChatService;
import org.springframework.web.bind.annotation.*;

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
    public ChatResponse askQuestion(
            @RequestBody
            ChatRequest request) {

        String answer =
                chatService
                        .askQuestion(
                                request.getQuestion()
                        );

        return new ChatResponse(
                answer
        );
    }
}