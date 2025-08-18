package com.handoff.model.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.model.entity.Message;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class MessageResponse {
    private UUID id;
    private UUID projectId;
    private UUID senderId;
    private String content;
    private JsonNode attachmentsJson;
    private Instant createdAt;

    public static MessageResponse from(Message m) {
        MessageResponse r = new MessageResponse();
        r.setId(m.getId());
        if (m.getProject() != null) r.setProjectId(m.getProject().getId());
        if (m.getSender() != null) r.setSenderId(m.getSender().getId());
        r.setContent(m.getContent() != null ? StringEscapeUtils.escapeHtml4(m.getContent()) : null);
        r.setAttachmentsJson(m.getAttachmentsJson());
        r.setCreatedAt(m.getCreatedAt());
        return r;
    }
}
