package com.practice.info_pilot_api.controller;

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
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;


    @GetMapping("/all")
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @PostMapping("/upload")
    public UploadResponse uploadDocument( @RequestParam("file") MultipartFile file) {

        System.out.println("Received File : " + file.getOriginalFilename());
        System.out.println("Size : "+ file.getSize());

        return documentService.uploadDocument(file);
    }
}