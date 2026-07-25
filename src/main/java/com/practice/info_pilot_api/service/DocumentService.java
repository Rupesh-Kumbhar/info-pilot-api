package com.practice.info_pilot_api.service;
import com.practice.info_pilot_api.dto.DocumentResponse;
import com.practice.info_pilot_api.dto.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    UploadResponse uploadDocument(MultipartFile file);
    List<DocumentResponse> getAllDocuments();

    void deleteDocumentById(Long id);
}
