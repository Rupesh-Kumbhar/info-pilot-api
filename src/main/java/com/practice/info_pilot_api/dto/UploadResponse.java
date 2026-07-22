package com.practice.info_pilot_api.dto;

public class UploadResponse {

    private String fileName;
    private String status;

    public UploadResponse() {
    }

    public UploadResponse(String fileName, String status) {
        this.fileName = fileName;
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStatus() {
        return status;
    }
}