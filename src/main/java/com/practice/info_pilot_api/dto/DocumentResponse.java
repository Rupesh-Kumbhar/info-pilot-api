package com.practice.info_pilot_api.dto;

public class DocumentResponse {

    private Long id;
    private String fileName;

    public DocumentResponse(Long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }
}