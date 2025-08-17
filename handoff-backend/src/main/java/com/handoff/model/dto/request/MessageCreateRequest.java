package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.handoff.util.SanitizeUtils.cleanInput;

@Data
public class MessageCreateRequest {
    @NotBlank
    @Size(max = 5000)
    private String content;

    private JsonNode attachmentsJson;

    public void sanitize() {
        this.content = cleanInput(this.content);
        // JSON left as-is
    }
}
