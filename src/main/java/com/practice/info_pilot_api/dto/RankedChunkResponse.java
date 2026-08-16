package com.practice.info_pilot_api.dto;

public class RankedChunkResponse {

    private Integer chunkNumber;

    private String chunkContent;

    private double score;

    private String documentName;

    public RankedChunkResponse(
            Integer chunkNumber,
            String chunkContent,
            double score,
            String documentName) {

        this.chunkNumber = chunkNumber;
        this.chunkContent = chunkContent;
        this.score = score;
        this.documentName = documentName;
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

    public String getDocumentName() {
        return documentName;
    }

}