package com.practice.info_pilot_api.controller;

import com.practice.info_pilot_api.dto.DocumentContentResponse;
import com.practice.info_pilot_api.dto.DocumentResponse;
import com.practice.info_pilot_api.dto.UploadResponse;
import com.practice.info_pilot_api.entity.Document;
import com.practice.info_pilot_api.repository.DocumentRepository;
import com.practice.info_pilot_api.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<DocumentResponse> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @PostMapping("/upload")
    public UploadResponse uploadDocument( @RequestParam("file") MultipartFile file) {

        System.out.println("Received File : " + file.getOriginalFilename());
        System.out.println("Size : "+ file.getSize());

        return documentService.uploadDocument(file);
    }

    @DeleteMapping("/{id}")
    public String deleteDocuments(@PathVariable Long id){
        documentService.deleteDocumentById(id);
        return "Document deleted successfully , DocId = " + id;
    }

    @GetMapping("/{id}/content")
    public DocumentContentResponse
    getDocumentContent(
            @PathVariable Long id) {

        return documentService
                .getDocumentContent(id);
    }
}