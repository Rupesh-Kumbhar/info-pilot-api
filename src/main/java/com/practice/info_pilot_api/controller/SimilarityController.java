package com.practice.info_pilot_api.controller;

import com.practice.info_pilot_api.dto.RankedChunkResponse;
import com.practice.info_pilot_api.service.SimilarityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/similarity")
@CrossOrigin(origins = "http://localhost:3000")
public class SimilarityController {

    private final SimilarityService similarityService;

    public SimilarityController(
            SimilarityService similarityService) {

        this.similarityService = similarityService;
    }

    @GetMapping("/{documentId}")
    public List<RankedChunkResponse> rankChunks(
            @PathVariable Long documentId) {

        return similarityService.rankChunks(documentId);
    }
}