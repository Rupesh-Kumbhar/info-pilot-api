package com.practice.info_pilot_api.service;
import com.practice.info_pilot_api.dto.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    UploadResponse uploadDocument(MultipartFile file);
}
