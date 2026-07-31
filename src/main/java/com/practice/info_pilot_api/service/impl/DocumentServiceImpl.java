package com.practice.info_pilot_api.service.impl;

import com.practice.info_pilot_api.dto.ChunkResponse;
import com.practice.info_pilot_api.dto.DocumentContentResponse;
import com.practice.info_pilot_api.dto.DocumentResponse;
import com.practice.info_pilot_api.repository.DocumentChunkRepository;
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
import com.practice.info_pilot_api.util.PdfUtil;
import com.practice.info_pilot_api.entity.DocumentChunk;
import com.practice.info_pilot_api.repository.DocumentChunkRepository;
import com.practice.info_pilot_api.util.ChunkUtil;

@Service
public class DocumentServiceImpl implements DocumentService {

        private static final String UPLOAD_DIR = "uploads";

        @Autowired
        private DocumentRepository documentRepository;

        @Autowired
        private DocumentChunkRepository documentChunkRepository;

        @Override
        public UploadResponse uploadDocument(MultipartFile file) {

                try {

                        String filePath =
                                FileUtil.saveFile(
                                        file,
                                        UPLOAD_DIR
                                );

                        String extractedText =
                                PdfUtil.extractText(
                                        filePath
                                );

                        Document document = new Document();

                        document.setFileName(
                                file.getOriginalFilename()
                        );

                        document.setOriginalFileName(
                                file.getOriginalFilename()
                        );

                        document.setFilePath(
                                filePath
                        );

                        document.setExtractedText(
                                extractedText
                        );

                        document.setUploadedAt(
                                LocalDateTime.now()
                        );

                        Document saved =
                                documentRepository.save(
                                        document
                                );
                        List<String> chunks =
                                ChunkUtil.createChunks(
                                        extractedText
                                );

                        int chunkNumber = 1;

                        for (String chunk : chunks) {

                                DocumentChunk documentChunk =
                                        new DocumentChunk();

                                documentChunk.setChunkNumber(
                                        chunkNumber++
                                );

                                documentChunk.setChunkContent(
                                        chunk
                                );

                                documentChunk.setDocument(
                                        saved
                                );

                                documentChunkRepository.save(
                                        documentChunk
                                );
                        }

                        return new UploadResponse(
                                saved.getId(),
                                saved.getFileName(),
                                "SUCCESS"
                        );

                } catch (IOException ex) {

                        throw new RuntimeException(
                                "File upload failed",
                                ex
                        );
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

        @Override
        public DocumentContentResponse getDocumentContent(Long id) {

                Document document = documentRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Document not found" + " with id : " + id));

                return new DocumentContentResponse(
                        document.getId(),
                        document.getFileName(),
                        document.getExtractedText()
                );
        }

        @Override
        public List<ChunkResponse>
        getDocumentChunks(Long documentId) {

                return documentChunkRepository.findByDocumentId(documentId)
                        .stream()
                        .map(chunk -> new ChunkResponse(chunk.getChunkNumber(),chunk.getChunkContent()))
                        .toList();
        }

        @Override
        public String findRelevantContext(String question) {

                String lowerQuestion = question.toLowerCase();

                StringBuilder context = new StringBuilder();

                documentChunkRepository.findAll().forEach(chunk -> {

                        String chunkText = chunk.getChunkContent().toLowerCase();

                        boolean match = lowerQuestion.split(" ").length > 0;

                        for (String word : lowerQuestion.split(" ")) {

                                if (chunkText.contains(word)) {
                                        context.append(chunk.getChunkContent());
                                        context.append("\n\n");
                                        break;
                                }
                        }
                });

                return context.toString();
        }

        @Override
        public String findSourceDocument(
                String question) {

                String lowerQuestion = question.toLowerCase();

                for (DocumentChunk chunk : documentChunkRepository.findAll()) {

                        String chunkText = chunk.getChunkContent().toLowerCase();

                        for (String word : lowerQuestion.split(" ")) {

                                if (chunkText.contains(word)) {
                                        return chunk.getDocument()
                                                .getOriginalFileName();
                                }
                        }
                }

                return "Unknown";
        }
}