package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.dto.RankedChunkResponse;
import com.practice.info_pilot_api.repository.DocumentChunkRepository;
import com.practice.info_pilot_api.service.EmbeddingService;
import com.practice.info_pilot_api.service.SimilarityService;
import com.practice.info_pilot_api.util.EmbeddingParserUtil;
import com.practice.info_pilot_api.util.SimilarityUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SimilarityServiceImpl
        implements SimilarityService {

    private final DocumentChunkRepository documentChunkRepository;

        private final EmbeddingService embeddingService;

        public SimilarityServiceImpl(DocumentChunkRepository documentChunkRepository, EmbeddingService embeddingService) {

                this.documentChunkRepository = documentChunkRepository;
            this.embeddingService = embeddingService;
        }

        @Override
        public List<RankedChunkResponse> getTopChunks(String question,Long documentId,Integer topK) {

            String questionEmbedding =
                    embeddingService
                            .generateEmbedding(
                                    question
                            );

            double[] questionVector =
                    EmbeddingParserUtil
                            .parseEmbedding(
                                    questionEmbedding
                            );

    @Override
    public List<RankedChunkResponse>
    getTopChunks(
            String question,
            Long documentId,
            Integer topK) {

        String questionEmbedding =
                embeddingService
                        .generateEmbedding(
                                question
                        );

                                        return new RankedChunkResponse(
                                                        chunk.getChunkNumber(),
                                                        chunk.getChunkContent(),
                                                        score);
                                })
                                .sorted(Comparator.comparing(RankedChunkResponse::getScore).reversed())
                                .limit(topK)
                                .toList();
        }

        @Override
        public String buildContext(
                        String question,
                        Long documentId,
                        Integer topK) {

                StringBuilder context = new StringBuilder();

                getTopChunks(
                                question,
                                documentId,
                                topK).forEach(chunk -> {

                                        context.append(
                                                        chunk.getChunkContent());

                                        context.append("\n\n");
                                });

                return context.toString();
        }
}
