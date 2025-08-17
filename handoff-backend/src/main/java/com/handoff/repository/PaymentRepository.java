package com.handoff.repository;

import com.handoff.model.entity.Payment;
import com.handoff.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByProjectId(UUID projectId);
    List<Payment> findByStatus(PaymentStatus status);
}

