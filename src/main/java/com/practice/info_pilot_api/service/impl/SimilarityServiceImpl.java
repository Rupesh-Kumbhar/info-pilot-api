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
public class SimilarityServiceImpl implements SimilarityService {

    private final DocumentChunkRepository documentChunkRepository;
    private final EmbeddingService embeddingService;

    public SimilarityServiceImpl(
            DocumentChunkRepository documentChunkRepository,
            EmbeddingService embeddingService) {
        this.documentChunkRepository = documentChunkRepository;
        this.embeddingService = embeddingService;
    }

    @Override
    public List<RankedChunkResponse> getTopChunks(
            String question,
            Long documentId,
            Integer topK) {

        String questionEmbedding =
                embeddingService.generateEmbedding(question);

        double[] questionVector =
                EmbeddingParserUtil.parseEmbedding(questionEmbedding);

        return documentChunkRepository.findByDocumentId(documentId)
                .stream()
                .map(chunk -> {
                    double[] chunkVector =
                            EmbeddingParserUtil.parseEmbedding(
                                    chunk.getEmbedding());

                    double score =
                            SimilarityUtil.cosineSimilarity(
                                    questionVector,
                                    chunkVector);

                    return new RankedChunkResponse(
                            chunk.getChunkNumber(),
                            chunk.getChunkContent(),
                            score,
                            chunk.getDocument()
                                    .getOriginalFileName()
                    );
                })
                .sorted(
                        Comparator.comparing(
                                        RankedChunkResponse::getScore)
                                .reversed())
                .limit(topK)
                .toList();
    }

    @Override
    public String buildContext(
            String question,
            Long documentId,
            Integer topK) {

        StringBuilder context =
                new StringBuilder();

        int rank = 1;

        for (RankedChunkResponse chunk :
                getTopChunks(
                        question,
                        documentId,
                        topK
                )) {

            context.append(
                    "Relevant Chunk #"
            );

            context.append(rank++);

            context.append("\n");

            context.append(
                    "Similarity Score : "
            );

            context.append(
                    String.format(
                            "%.4f",
                            chunk.getScore()
                    )
            );

            context.append("\n\n");

            context.append(
                    chunk.getChunkContent()
            );

            context.append(
                    "\n\n---------------------------------\n\n"
            );
        }

        return context.toString();
    }

    @Override
    public List<RankedChunkResponse> getTopChunksAcrossDocuments(
                    String question,
                    Integer topK) {

            String questionEmbedding = embeddingService.generateEmbedding(
                            question);

            double[] questionVector = EmbeddingParserUtil.parseEmbedding(
                            questionEmbedding);

            return documentChunkRepository
                            .findAll()
                            .stream()
                            .map(chunk -> {

                                    double[] chunkVector = EmbeddingParserUtil
                                                    .parseEmbedding(
                                                                    chunk.getEmbedding());

                                    double score = SimilarityUtil
                                                    .cosineSimilarity(
                                                                    questionVector,
                                                                    chunkVector);

                                    return new RankedChunkResponse(
                                                    chunk.getChunkNumber(),
                                                    chunk.getChunkContent(),
                                                    score,
                                                    chunk.getDocument()
                                                                    .getOriginalFileName());
                            })
                            .sorted(
                                            Comparator.comparing(
                                                            RankedChunkResponse::getScore).reversed())
                            .limit(topK)
                            .toList();
    }


    @Override
    public String buildContextAcrossDocuments(
            String question,
            Integer topK) {

        StringBuilder context =
                new StringBuilder();

        int rank = 1;

        for (RankedChunkResponse chunk :
                getTopChunksAcrossDocuments(
                        question,
                        topK
                )) {

            context.append(
                    "Document : "
            );

            context.append(
                    chunk.getDocumentName()
            );

            context.append("\n");

            context.append(
                    "Rank : "
            );

            context.append(rank++);

            context.append("\n");

            context.append(
                    "Score : "
            );

            context.append(
                    String.format(
                            "%.4f",
                            chunk.getScore()
                    )
            );

            context.append("\n\n");

            context.append(
                    chunk.getChunkContent()
            );

            context.append(
                    "\n\n--------------------------------\n\n"
            );
        }

        return context.toString();
    }
}
