package com.handoff.model.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.model.entity.Payment;
import com.handoff.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class PaymentResponse {
    private UUID id;
    private UUID projectId;
    private UUID payerId;
    private UUID payeeId;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private String escrowReference;
    private JsonNode metadataJson;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant capturedAt;
    private Instant refundedAt;

    public static PaymentResponse from(Payment p) {
        PaymentResponse r = new PaymentResponse();
        r.setId(p.getId());
        if (p.getProject() != null) r.setProjectId(p.getProject().getId());
        if (p.getPayer() != null) r.setPayerId(p.getPayer().getId());
        if (p.getPayee() != null) r.setPayeeId(p.getPayee().getId());
        r.setAmount(p.getAmount());
        r.setCurrency(p.getCurrency() != null ? StringEscapeUtils.escapeHtml4(p.getCurrency()) : null);
        r.setStatus(p.getStatus());
        r.setEscrowReference(p.getEscrowReference() != null ? StringEscapeUtils.escapeHtml4(p.getEscrowReference()) : null);
        r.setMetadataJson(p.getMetadataJson());
        r.setCreatedAt(p.getCreatedAt());
        r.setUpdatedAt(p.getUpdatedAt());
        r.setCapturedAt(p.getCapturedAt());
        r.setRefundedAt(p.getRefundedAt());
        return r;
    }
}
