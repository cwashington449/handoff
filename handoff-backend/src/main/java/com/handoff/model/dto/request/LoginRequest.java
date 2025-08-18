package com.handoff.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.handoff.util.SanitizeUtils.cleanInput;

@Data
public class LoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public void sanitize() {
        this.email = cleanInput(this.email);
        // Do not modify password
    }
}
