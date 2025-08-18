package com.handoff.service;

import com.handoff.exception.BadRequestException;
import com.handoff.exception.ForbiddenException;
import com.handoff.exception.ResourceNotFoundException;
import com.handoff.model.entity.Payment;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.model.enums.PaymentStatus;
import com.handoff.repository.PaymentRepository;
import com.handoff.repository.ProjectApplicationRepository;
import com.handoff.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final PaymentLookupService paymentLookupService;

    @Transactional
    public Payment create(UUID projectId, String creatorEmail, UUID payeeId, BigDecimal amount, String currency, com.fasterxml.jackson.databind.JsonNode metadataJson) {
        validateAmount(amount);
        Project project = projectService.getOrThrow(projectId);
        User creator = getProjectCreatorOrThrow(project, creatorEmail);
        User payee = getValidPayeeOrThrow(projectId, payeeId);

        Payment payment = new Payment();
        payment.setProject(project);
        payment.setPayer(creator);
        payment.setPayee(payee);
        payment.setAmount(amount);
        payment.setCurrency((currency != null && !currency.isBlank()) ? currency : null);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMetadataJson(metadataJson);
        return paymentRepository.save(payment);
    }

    public Payment getOrThrow(UUID id) {
        return paymentLookupService.getOrThrow(id);
    }

    public List<Payment> listByProject(UUID projectId, String requesterEmail) {
        return paymentLookupService.listByProject(projectId, requesterEmail);
    }

    public List<Payment> listByStatus(PaymentStatus status) {
        return paymentLookupService.listByStatus(status);
    }

    @Transactional
    public Payment release(UUID paymentId, String creatorEmail) {
        Payment payment = getOrThrow(paymentId);
        validateProjectCreatorOrThrow(payment.getProject(), creatorEmail, "release payments");
        validatePaymentStatus(payment, PaymentStatus.RELEASED, "Payment already released");
        if (payment.getStatus() == PaymentStatus.REFUNDED) {
            throw new BadRequestException("Cannot release a refunded payment");
        }
        payment.setStatus(PaymentStatus.RELEASED);
        payment.setCapturedAt(Instant.now());
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment refund(UUID paymentId, String creatorEmail) {
        Payment payment = getOrThrow(paymentId);
        validateProjectCreatorOrThrow(payment.getProject(), creatorEmail, "refund payments");
        validatePaymentStatus(payment, PaymentStatus.REFUNDED, "Payment already refunded");
        payment.setStatus(PaymentStatus.REFUNDED);
        payment.setRefundedAt(Instant.now());
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment update(UUID paymentId,
                          String creatorEmail,
                          BigDecimal amount,
                          String currency,
                          com.fasterxml.jackson.databind.JsonNode metadataJson) {
        Payment p = getOrThrow(paymentId);
        validateProjectCreatorOrThrow(p.getProject(), creatorEmail, "update payments");
        if (p.getStatus() != PaymentStatus.PENDING) {
            throw new BadRequestException("Only pending payments can be updated");
        }
        if (amount != null) {
            validateAmount(amount);
            p.setAmount(amount);
        }
        if (currency != null && !currency.isBlank()) {
            p.setCurrency(currency);
        }
        if (metadataJson != null) {
            p.setMetadataJson(metadataJson);
        }
        return paymentRepository.save(p);
    }

    @Transactional
    public void delete(UUID paymentId, String creatorEmail) {
        Payment p = getOrThrow(paymentId);
        validateProjectCreatorOrThrow(p.getProject(), creatorEmail, "delete payments");
        if (p.getStatus() == PaymentStatus.RELEASED) {
            throw new BadRequestException("Cannot delete a released payment");
        }
        paymentRepository.delete(p);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
    }

    private User getProjectCreatorOrThrow(Project project, String creatorEmail) {
        User creator = userService.findByEmailOrThrow(creatorEmail);
        if (!isProjectCreator(project, creator)) {
            throw new ForbiddenException("Only project creator can perform this action");
        }
        return creator;
    }

    private void validateProjectCreatorOrThrow(Project project, String creatorEmail, String action) {
        User creator = userService.findByEmailOrThrow(creatorEmail);
        if (!isProjectCreator(project, creator)) {
            throw new ForbiddenException("Only project creator can " + action);
        }
    }

    private User getValidPayeeOrThrow(UUID projectId, UUID payeeId) {
        User payee = userRepository.findById(payeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!applicationRepository.existsByProjectIdAndFinisherId(projectId, payeeId)) {
            throw new BadRequestException("Payee must have applied to this project");
        }
        return payee;
    }

    private boolean isAuthorized(Project project, User user) {
        return isProjectCreator(project, user) || applicationRepository.existsByProjectIdAndFinisherId(project.getId(), user.getId());
    }

    private boolean isProjectCreator(Project project, User user) {
        return project.getCreator() != null && project.getCreator().getId().equals(user.getId());
    }

    private List<Payment> filterPaymentsForRequester(List<Payment> payments, User requester) {
        return payments.stream()
                .filter(payment -> payment.getPayee() != null && payment.getPayee().getId().equals(requester.getId()))
                .toList();
    }

    private void validatePaymentStatus(Payment payment, PaymentStatus invalidStatus, String message) {
        if (payment.getStatus() == invalidStatus) {
            throw new BadRequestException(message);
        }
    }
}