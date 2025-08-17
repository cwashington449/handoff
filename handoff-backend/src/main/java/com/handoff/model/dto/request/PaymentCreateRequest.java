package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import static com.handoff.util.SanitizeUtils.cleanInput;

@Data
public class PaymentCreateRequest {
    @NotNull
    @Positive
    private BigDecimal amount;

    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    private UUID payeeId;

    private JsonNode metadataJson;

    public void sanitize() {
        this.currency = cleanInput(this.currency);
        // amount, payeeId and JSON left as-is
    }
}
