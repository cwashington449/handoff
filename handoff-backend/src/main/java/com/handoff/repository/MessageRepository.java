package com.handoff.repository;

import com.handoff.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByProjectId(UUID projectId);
    List<Message> findBySenderId(UUID senderId);
}

