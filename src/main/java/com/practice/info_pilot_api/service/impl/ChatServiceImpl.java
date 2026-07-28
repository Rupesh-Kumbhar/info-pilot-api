package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.service.ChatService;
import com.practice.info_pilot_api.service.DocumentService;
import com.practice.info_pilot_api.service.GeminiService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl
        implements ChatService {

    private final GeminiService geminiService;
    private final DocumentService documentService;

    public ChatServiceImpl(
            GeminiService geminiService,
            DocumentService documentService) {

        this.geminiService = geminiService;
        this.documentService = documentService;
    }

//    public ChatServiceImpl(
//            GeminiService geminiService) {
//
//        this.geminiService = geminiService;
//    }

    @Override
    public String askQuestion(
            String question) {

        String context =
                documentService.findRelevantContext(question);

        String finalPrompt =
                """
                You are an enterprise knowledge assistant.
        
                Use ONLY the following context
                to answer the question.
        
                Context:
                %s
        
                Question:
                %s
        
                If the answer is not present
                in the context, say:
        
                "I could not find this
                information in the uploaded
                documents."
                """
                .formatted(context,question);

        System.out.println("Retrieved Context:");
        System.out.println(context);
        return geminiService.askQuestion(finalPrompt);
    }
}