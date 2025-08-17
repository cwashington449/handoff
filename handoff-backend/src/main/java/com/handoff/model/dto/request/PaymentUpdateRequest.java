package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

import static com.handoff.util.SanitizeUtils.cleanInput;

@Data
public class PaymentUpdateRequest {
    @Positive
    private BigDecimal amount;

    @Size(min = 3, max = 3)
    private String currency;

    private JsonNode metadataJson;

    public void sanitize() {
        this.currency = cleanInput(this.currency);
        // amount and JSON left as-is
    }
}

