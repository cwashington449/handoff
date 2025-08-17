package com.handoff.model.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.model.entity.ProjectApplication;
import com.handoff.model.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ProjectApplicationResponse {
    private UUID id;
    private UUID projectId;
    private UUID finisherId;
    private ApplicationStatus status;
    private String coverLetter;
    private BigDecimal bidAmount;
    private String proposedTimeline;
    private JsonNode attachmentsJson;
    private Instant createdAt;
    private Instant updatedAt;

    public static ProjectApplicationResponse from(ProjectApplication a) {
        ProjectApplicationResponse r = new ProjectApplicationResponse();
        r.setId(a.getId());
        if (a.getProject() != null) r.setProjectId(a.getProject().getId());
        if (a.getFinisher() != null) r.setFinisherId(a.getFinisher().getId());
        r.setStatus(a.getStatus());
        r.setCoverLetter(a.getCoverLetter() != null ? StringEscapeUtils.escapeHtml4(a.getCoverLetter()) : null);
        r.setBidAmount(a.getBidAmount());
        r.setProposedTimeline(a.getProposedTimeline() != null ? StringEscapeUtils.escapeHtml4(a.getProposedTimeline()) : null);
        r.setAttachmentsJson(a.getAttachmentsJson());
        r.setCreatedAt(a.getCreatedAt());
        r.setUpdatedAt(a.getUpdatedAt());
        return r;
    }
}
