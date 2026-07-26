package com.practice.info_pilot_api.dto;

public class DocumentContentResponse {

    private Long id;

    private String fileName;

    private String content;

    public DocumentContentResponse(
            Long id,
            String fileName,
            String content) {

        this.id = id;
        this.fileName = fileName;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }
}