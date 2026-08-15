package com.practice.info_pilot_api.controller;

import com.practice.info_pilot_api.dto.RankedChunkResponse;
import com.practice.info_pilot_api.dto.SemanticSearchResponse;
import com.practice.info_pilot_api.service.SimilarityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semantic-search")
@CrossOrigin(origins = "http://localhost:3000")
public class SemanticSearchController {

    private final SimilarityService similarityService;

    public SemanticSearchController(
            SimilarityService similarityService) {

        this.similarityService = similarityService;
    }

    @GetMapping
    public SemanticSearchResponse search(
            @RequestParam String question,
            @RequestParam Long documentId) {

        List<RankedChunkResponse> chunks =
                similarityService.getTopChunks(
                        question,
                        documentId,
                        3
                );

        String context =
                similarityService.buildContext(
                        question,
                        documentId,
                        3
                );

        SemanticSearchResponse response =
                new SemanticSearchResponse(
                        question,
                        context,
                        chunks
                );

        response.setSelectedChunkCount(
                chunks.size()
        );

        response.setHighestScore(
                chunks.isEmpty()
                        ? 0.0
                        : chunks.get(0).getScore()
        );

        return response;
    }
}