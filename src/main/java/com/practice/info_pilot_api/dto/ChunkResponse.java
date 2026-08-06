package com.practice.info_pilot_api.dto;

public class ChunkResponse {

    private Integer chunkNumber;

    private String chunkContent;

    private String embedding;

    public ChunkResponse(
            Integer chunkNumber,
            String chunkContent,
            String embedding) {

        this.chunkNumber = chunkNumber;
        this.chunkContent = chunkContent;
        this.embedding = embedding;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public String getChunkContent() {
        return chunkContent;
    }

    public String getEmbedding() {
        return embedding;
    }
}