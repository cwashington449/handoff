package com.handoff.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.exception.ForbiddenException;
import com.handoff.exception.InternalServerErrorException;
import com.handoff.exception.ResourceNotFoundException;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.model.enums.ProjectStatus;
import com.handoff.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Project create(User creator,
                          String title,
                          String description,
                          JsonNode requirementsJson,
                          BigDecimal budgetMin,
                          BigDecimal budgetMax,
                          String budgetCurrency,
                          String estimatedTimeline,
                          Set<String> requiredSkills,
                          JsonNode attachmentsJson,
                          Instant applicationDeadline) {
        Project p = new Project();
        p.setCreator(creator);
        p.setTitle(title);
        p.setDescription(description);
        p.setRequirementsJson(requirementsJson);
        p.setBudgetMin(budgetMin);
        p.setBudgetMax(budgetMax);
        if (budgetCurrency != null && !budgetCurrency.isBlank()) {
            p.setBudgetCurrency(budgetCurrency);
        }
        p.setEstimatedTimeline(estimatedTimeline);
        if (requiredSkills != null) {
            p.setRequiredSkills(requiredSkills);
        }
        p.setAttachmentsJson(attachmentsJson);
        p.setApplicationDeadline(applicationDeadline);
        // status defaults to DRAFT
        return projectRepository.save(p);
    }

    @Transactional(readOnly = true)
    public Project getOrThrow(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    @Transactional(readOnly = true)
    public List<Project> listMine(UUID creatorId) {
        return projectRepository.findByCreatorId(creatorId);
    }

    @Transactional(readOnly = true)
    public List<Project> listByStatus(ProjectStatus status) {
        return projectRepository.findByStatus(status);
    }

    @Transactional
    public Project update(UUID id,
                          String requesterEmail,
                          String title,
                          String description,
                          JsonNode requirementsJson,
                          BigDecimal budgetMin,
                          BigDecimal budgetMax,
                          String budgetCurrency,
                          String estimatedTimeline,
                          Set<String> requiredSkills,
                          JsonNode attachmentsJson,
                          Instant applicationDeadline) {
        Project p = getOrThrow(id);
        ensureOwner(p, requesterEmail);
        if (title != null) p.setTitle(title);
        if (description != null) p.setDescription(description);
        if (requirementsJson != null) p.setRequirementsJson(requirementsJson);
        if (budgetMin != null) p.setBudgetMin(budgetMin);
        if (budgetMax != null) p.setBudgetMax(budgetMax);
        if (budgetCurrency != null) p.setBudgetCurrency(budgetCurrency);
        if (estimatedTimeline != null) p.setEstimatedTimeline(estimatedTimeline);
        if (requiredSkills != null) p.setRequiredSkills(requiredSkills);
        if (attachmentsJson != null) p.setAttachmentsJson(attachmentsJson);
        if (applicationDeadline != null) p.setApplicationDeadline(applicationDeadline);
        return projectRepository.save(p);
    }

    @Transactional
    public Project publish(UUID id, String requesterEmail) {
        Project p = getOrThrow(id);
        ensureOwner(p, requesterEmail);
        p.setStatus(ProjectStatus.OPEN);
        p.setPublishedAt(Instant.now());
        return projectRepository.save(p);
    }

    @Transactional
    public void delete(UUID id, String requesterEmail) {
        Project p = getOrThrow(id);
        ensureOwner(p, requesterEmail);
        projectRepository.delete(p);
    }

    @Transactional
    public void incrementViewCount(UUID id) {
        Project p = getOrThrow(id);
        int current = p.getViewCount() == null ? 0 : p.getViewCount();
        p.setViewCount(current + 1);
        projectRepository.save(p);
    }

    private void ensureOwner(Project p, String requesterEmail) {
        User owner = p.getCreator();
        if (owner == null) {
            throw new InternalServerErrorException("Project has no creator set");
        }
        if (!owner.getEmail().equalsIgnoreCase(requesterEmail)) {
            throw new ForbiddenException("Only the creator can modify the project");
        }
    }
}
