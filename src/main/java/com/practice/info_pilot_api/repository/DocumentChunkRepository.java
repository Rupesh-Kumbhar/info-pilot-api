package com.practice.info_pilot_api.repository;

import com.practice.info_pilot_api.entity.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, Long> {

    List<DocumentChunk> findByDocumentId(Long documentId);
    List<DocumentChunk> findAll();
}