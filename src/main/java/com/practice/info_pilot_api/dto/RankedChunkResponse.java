package com.practice.info_pilot_api.dto;

public class RankedChunkResponse {

    private Integer chunkNumber;

    private String chunkContent;

    private double score;

    public RankedChunkResponse(
            Integer chunkNumber,
            String chunkContent,
            double score) {

        this.chunkNumber = chunkNumber;
        this.chunkContent = chunkContent;
        this.score = score;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public String getChunkContent() {
        return chunkContent;
    }

    public double getScore() {
        return score;
    }
}