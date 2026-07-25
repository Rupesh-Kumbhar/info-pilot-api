package com.practice.info_pilot_api.dto;

public class UploadResponse {

    private Long id;
    private String fileName;
    private String status;

    public UploadResponse() {
    }

    public UploadResponse(Long id,String fileName,String status) {
        this.id = id;
        this.fileName = fileName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStatus() {
        return status;
    }
}