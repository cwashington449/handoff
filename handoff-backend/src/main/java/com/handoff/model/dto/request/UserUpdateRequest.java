package com.handoff.model.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

import static com.handoff.util.SanitizeUtils.cleanInput;
import static com.handoff.util.SanitizeUtils.cleanInputSet;

@Data
public class UserUpdateRequest {
    @Size(max = 100)
    private String firstName;
    @Size(max = 100)
    private String lastName;

    private JsonNode profileJson;
    private JsonNode preferencesJson;
    private Set<String> skills;

    public void sanitize() {
        this.firstName = cleanInput(this.firstName);
        this.lastName = cleanInput(this.lastName);
        this.skills = cleanInputSet(this.skills);
        // JSON left as-is
    }
}

