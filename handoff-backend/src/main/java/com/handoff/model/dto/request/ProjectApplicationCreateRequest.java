package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

import static com.handoff.util.SanitizeUtils.cleanInput;

@Data
public class ProjectApplicationCreateRequest {
    @Size(max = 5000)
    private String coverLetter;

    private BigDecimal bidAmount;

    @Size(max = 100)
    private String proposedTimeline;

    private JsonNode attachmentsJson;

    public void sanitize() {
        this.coverLetter = cleanInput(this.coverLetter);
        this.proposedTimeline = cleanInput(this.proposedTimeline);
        // bidAmount and JSON left as-is
    }
}
