package com.practice.info_pilot_api.controller;

import com.practice.info_pilot_api.dto.MultiDocumentChatResponse;
import com.practice.info_pilot_api.service.GeminiService;
import com.practice.info_pilot_api.service.SimilarityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/multi-document-chat")
@CrossOrigin(origins = "http://localhost:3000")
public class MultiDocumentChatController {

    private final SimilarityService similarityService;

    private final GeminiService geminiService;

    public MultiDocumentChatController(
            SimilarityService similarityService,
            GeminiService geminiService) {

        this.similarityService =
                similarityService;

        this.geminiService =
                geminiService;
    }

    @GetMapping
    public MultiDocumentChatResponse askQuestion(
            @RequestParam String question) {

        String context =
                similarityService
                        .buildContextAcrossDocuments(
                                question,
                                5
                        );

        String prompt = """
                You are an enterprise knowledge assistant.

                Use ONLY the context below.

                Context:
                %s

                Question:
                %s
                """.formatted(
                context,
                question
        );

        String answer =
                geminiService
                        .askQuestion(
                                prompt
                        );

        return new MultiDocumentChatResponse(
                answer
        );
    }
}