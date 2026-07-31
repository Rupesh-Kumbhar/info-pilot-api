package com.practice.info_pilot_api.repository;

import com.practice.info_pilot_api.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}