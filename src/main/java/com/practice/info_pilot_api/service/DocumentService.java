package com.practice.info_pilot_api.service;

import com.practice.info_pilot_api.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

        UploadResponse uploadDocument(
                        MultipartFile file);

        List<DocumentResponse> getAllDocuments();

        void deleteDocumentById(
                        Long id);

        DocumentContentResponse getDocumentContent(
                        Long id);

        List<ChunkResponse> getDocumentChunks(
                        Long documentId);

        String findRelevantContext(
                        String question,
                        Long documentId);

        String findSourceDocument(
                        Long documentId);

        List<DocumentDropdownResponse> getDocumentsForDropdown();
}