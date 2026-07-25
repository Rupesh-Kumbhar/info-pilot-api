package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.dto.DocumentResponse;
import com.practice.info_pilot_api.service.DocumentService;
import org.springframework.stereotype.Service;

/*
Upload File
Extract Text
Call Gemini
 */

import com.practice.info_pilot_api.dto.UploadResponse;
import com.practice.info_pilot_api.entity.Document;
import com.practice.info_pilot_api.repository.DocumentRepository;
import com.practice.info_pilot_api.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import com.practice.info_pilot_api.exception.ResourceNotFoundException;

@Service
public class DocumentServiceImpl implements DocumentService {

        private static final String UPLOAD_DIR = "uploads";

        @Autowired
        private DocumentRepository documentRepository;

        @Override
        public UploadResponse uploadDocument(MultipartFile file) {

                try {
                        String filePath = FileUtil.saveFile(file,UPLOAD_DIR);
                        Document document = new Document();
                        document.setFileName(file.getOriginalFilename());
                        document.setOriginalFileName(file.getOriginalFilename());
                        document.setFilePath(filePath);
                        document.setUploadedAt(LocalDateTime.now());
                        Document saved = documentRepository.save(document);

                        return new UploadResponse(saved.getId(),saved.getFileName(),"SUCCESS");

                } catch (IOException ex) {
                        throw new RuntimeException("File upload failed");
                }
        }

        @Override
        public List<DocumentResponse> getAllDocuments() {

                return documentRepository
                        .findAll()
                        .stream()
                        .map(document ->
                                new DocumentResponse(
                                        document.getId(),
                                        document.getFileName()
                                ))
                        .toList();
        }

        @Override
        public void deleteDocumentById(Long id) {

                Document document = documentRepository.findById(id)
                                .orElseThrow(() ->
                                new ResourceNotFoundException(
                                "Document not found with id : " + id));

                try {
                        FileUtil.deleteFile(document.getFilePath());
                } catch (IOException ex) {

                        throw new RuntimeException("Unable to delete file",ex);
                }

                documentRepository.delete(document);
        }
}