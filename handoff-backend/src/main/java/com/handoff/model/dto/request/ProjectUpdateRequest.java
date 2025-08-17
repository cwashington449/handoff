package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class ProjectUpdateRequest {
    @Size(max = 255)
    private String title;

    private String description;

    private JsonNode requirementsJson;

    private BigDecimal budgetMin;
    private BigDecimal budgetMax;

    @Size(min = 3, max = 3)
    private String budgetCurrency;

    @Size(max = 100)
    private String estimatedTimeline;

    private Set<String> requiredSkills;

    private JsonNode attachmentsJson;

    private Instant applicationDeadline;
}

