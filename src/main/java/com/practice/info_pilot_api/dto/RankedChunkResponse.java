package com.practice.info_pilot_api.dto;

public class RankedChunkResponse {

    private String chunkContent;

    private double score;

    public RankedChunkResponse(String chunkContent,double score) {
        this.chunkContent = chunkContent;
        this.score = score;
    }

    public String getChunkContent() {
        return chunkContent;
    }

    public double getScore() {
        return score;
    }
}