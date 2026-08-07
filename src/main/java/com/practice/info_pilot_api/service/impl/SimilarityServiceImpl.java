package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.dto.RankedChunkResponse;
import com.practice.info_pilot_api.repository.DocumentChunkRepository;
import com.practice.info_pilot_api.service.SimilarityService;
import com.practice.info_pilot_api.util.SimilarityUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SimilarityServiceImpl
                implements SimilarityService {

        private final DocumentChunkRepository documentChunkRepository;

        public SimilarityServiceImpl(DocumentChunkRepository documentChunkRepository) {

                this.documentChunkRepository = documentChunkRepository;
        }

        @Override
        public List<RankedChunkResponse> getTopChunks(Long documentId,Integer topK) {

                double[] questionVector = {
                                1.0,
                                2.0,
                                3.0
                };

                return documentChunkRepository
                                .findByDocumentId(documentId)
                                .stream()
                                .map(chunk -> {

                                        double[] chunkVector = {
                                                        1.0,
                                                        2.0,
                                                        3.0
                                        };

                                        double score = SimilarityUtil
                                                        .cosineSimilarity(
                                                                        questionVector,
                                                                        chunkVector);

                                        return new RankedChunkResponse(
                                                        chunk.getChunkNumber(),
                                                        chunk.getChunkContent(),
                                                        score);
                                })
                                .sorted(Comparator.comparing(RankedChunkResponse::getScore).reversed())
                                .limit(topK)
                                .toList();
        }
}