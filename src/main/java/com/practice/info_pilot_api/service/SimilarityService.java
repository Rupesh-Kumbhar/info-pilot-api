package com.practice.info_pilot_api.service;

import com.practice.info_pilot_api.dto.RankedChunkResponse;

import java.util.List;

public interface SimilarityService {

        List<RankedChunkResponse> getTopChunks(String question,Long documentId,Integer topK);

        String buildContext(String question,Long documentId,Integer topK);

        List<RankedChunkResponse>
        getTopChunksAcrossDocuments(
                String question,
                Integer topK
        );

        String buildContextAcrossDocuments(
                String question,
                Integer topK
        );
}
