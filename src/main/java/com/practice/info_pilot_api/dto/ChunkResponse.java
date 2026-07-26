package com.practice.info_pilot_api.dto;

public class ChunkResponse {

    private Integer chunkNumber;

    private String content;

    public ChunkResponse(
            Integer chunkNumber,
            String content) {

        this.chunkNumber = chunkNumber;
        this.content = content;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public String getContent() {
        return content;
    }
}