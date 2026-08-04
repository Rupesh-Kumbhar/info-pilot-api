package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.dto.ChatHistoryResponse;
import com.practice.info_pilot_api.dto.ChatResponse;
import com.practice.info_pilot_api.entity.ChatMessage;
import com.practice.info_pilot_api.repository.ChatMessageRepository;
import com.practice.info_pilot_api.service.ChatService;
import com.practice.info_pilot_api.service.DocumentService;
import com.practice.info_pilot_api.service.GeminiService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

        private final GeminiService geminiService;
        private final DocumentService documentService;
        private final ChatMessageRepository chatMessageRepository;

        public ChatServiceImpl(
                        GeminiService geminiService,
                        DocumentService documentService, ChatMessageRepository chatMessageRepository) {

                this.geminiService = geminiService;
                this.documentService = documentService;
                this.chatMessageRepository = chatMessageRepository;
        }

        // public ChatServiceImpl(
        // GeminiService geminiService) {
        //
        // this.geminiService = geminiService;
        // }

        @Override
        public ChatResponse askQuestion(
                        String question,
                        Long documentId) {

                String context = documentService.findRelevantContext(question, documentId);

                String sourceDocument = documentService.findSourceDocument(documentId);

                String finalPrompt = """
                                You are an enterprise knowledge assistant.

                                Use ONLY the context below.

                                Context:
                                %s

                                Question:
                                %s

                                If answer not found,
                                say:
                                I could not find this information in the uploaded documents.
                                """
                                .formatted(
                                                context,
                                                question);

                String answer = geminiService.askQuestion(finalPrompt);

                ChatMessage chatMessage = new ChatMessage();

                chatMessage.setQuestion(question);

                chatMessage.setAnswer(answer);

                chatMessage.setSourceDocument(sourceDocument);

                chatMessage.setAskedAt(java.time.LocalDateTime.now());

                chatMessageRepository.save(chatMessage);

                return new ChatResponse(answer, sourceDocument);
        }

        @Override
        public List<ChatHistoryResponse> getHistory() {

                return chatMessageRepository
                                .findAll()
                                .stream()
                                .map(chat ->

                                new ChatHistoryResponse(
                                                chat.getId(),
                                                chat.getQuestion(),
                                                chat.getAnswer(),
                                                chat.getSourceDocument(),
                                                chat.getAskedAt()))
                                .toList();
        }

        @Override
        public long getTotalQuestions() {
                return chatMessageRepository.count();
        }

        @Override
        public String getLastQuestion() {

                return chatMessageRepository
                                .findAll()
                                .stream()
                                .reduce(
                                                (first, second) -> second)
                                .map(
                                                ChatMessage::getQuestion)
                                .orElse(
                                                "No questions yet");
        }
}