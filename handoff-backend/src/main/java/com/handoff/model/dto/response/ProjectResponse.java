package com.handoff.model.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.model.entity.Project;
import com.handoff.model.enums.ProjectComplexity;
import com.handoff.model.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ProjectResponse {
    private UUID id;
    private UUID creatorId;

    private String title;
    private String description;

    private JsonNode requirementsJson;

    private BigDecimal budgetMin;
    private BigDecimal budgetMax;
    private String budgetCurrency;

    private ProjectComplexity complexity;
    private String estimatedTimeline;
    private Set<String> requiredSkills;

    private ProjectStatus status;
    private Instant publishedAt;
    private Instant applicationDeadline;

    private JsonNode attachmentsJson;

    private Integer viewCount;
    private Integer applicationCount;

    private Instant createdAt;
    private Instant updatedAt;

    public static ProjectResponse from(Project p) {
        ProjectResponse r = new ProjectResponse();
        r.setId(p.getId());
        if (p.getCreator() != null) {
            r.setCreatorId(p.getCreator().getId());
        }
        r.setTitle(p.getTitle());
        r.setDescription(p.getDescription());
        r.setRequirementsJson(p.getRequirementsJson());
        r.setBudgetMin(p.getBudgetMin());
        r.setBudgetMax(p.getBudgetMax());
        r.setBudgetCurrency(p.getBudgetCurrency());
        r.setComplexity(p.getComplexity());
        r.setEstimatedTimeline(p.getEstimatedTimeline());
        r.setRequiredSkills(p.getRequiredSkills());
        r.setStatus(p.getStatus());
        r.setPublishedAt(p.getPublishedAt());
        r.setApplicationDeadline(p.getApplicationDeadline());
        r.setAttachmentsJson(p.getAttachmentsJson());
        r.setViewCount(p.getViewCount());
        r.setApplicationCount(p.getApplicationCount());
        r.setCreatedAt(p.getCreatedAt());
        r.setUpdatedAt(p.getUpdatedAt());
        return r;
    }
}

