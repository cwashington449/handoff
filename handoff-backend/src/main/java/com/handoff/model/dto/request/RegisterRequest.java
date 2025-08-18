package com.handoff.model.dto.request;

import com.handoff.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.handoff.util.SanitizeUtils.cleanInput;

@Data
public class RegisterRequest {
    @Email @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private UserRole role;

    public void sanitize() {
        this.email = cleanInput(this.email);
        this.firstName = cleanInput(this.firstName);
        this.lastName = cleanInput(this.lastName);
        // Do not modify password or role
    }
}
