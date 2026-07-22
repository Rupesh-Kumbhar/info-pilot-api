package com.practice.info_pilot_api.repository;

import com.practice.info_pilot_api.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository
        extends JpaRepository<Document, Long> {
}