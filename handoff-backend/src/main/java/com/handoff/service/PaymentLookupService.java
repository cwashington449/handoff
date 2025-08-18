package com.handoff.service;

import com.handoff.exception.ForbiddenException;
import com.handoff.exception.ResourceNotFoundException;
import com.handoff.model.entity.Payment;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.model.enums.PaymentStatus;
import com.handoff.repository.PaymentRepository;
import com.handoff.repository.ProjectApplicationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentLookupService {
    private final PaymentRepository paymentRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectApplicationRepository applicationRepository;

    public PaymentLookupService(PaymentRepository paymentRepository,
                                ProjectService projectService,
                                UserService userService,
                                ProjectApplicationRepository applicationRepository) {
        this.paymentRepository = paymentRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.applicationRepository = applicationRepository;
    }

    @Cacheable(value = "paymentById", key = "#id")
    @Transactional(readOnly = true)
    public Payment getOrThrow(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }

    @Cacheable(value = "paymentsByProject", key = "#projectId + ':' + #requesterEmail")
    @Transactional(readOnly = true)
    public List<Payment> listByProject(UUID projectId, String requesterEmail) {
        Project project = projectService.getOrThrow(projectId);
        User requester = userService.findByEmailOrThrow(requesterEmail);
        if (!isAuthorized(project, requester)) {
            throw new ForbiddenException("Not allowed to view payments for this project");
        }
        List<Payment> payments = paymentRepository.findByProjectId(projectId);
        return isProjectCreator(project, requester) ? payments : filterPaymentsForRequester(payments, requester);
    }

    @Cacheable(value = "paymentsByStatus", key = "#status")
    @Transactional(readOnly = true)
    public List<Payment> listByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
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
}

