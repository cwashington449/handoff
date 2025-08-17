package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import static com.handoff.util.SanitizeUtils.cleanInput;
import static com.handoff.util.SanitizeUtils.cleanInputSet;

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

    public void sanitize() {
        this.title = cleanInput(this.title);
        this.description = cleanInput(this.description);
        this.budgetCurrency = cleanInput(this.budgetCurrency);
        this.estimatedTimeline = cleanInput(this.estimatedTimeline);
        this.requiredSkills = cleanInputSet(this.requiredSkills);
        // JSON/dates/numbers left as-is
    }
}
