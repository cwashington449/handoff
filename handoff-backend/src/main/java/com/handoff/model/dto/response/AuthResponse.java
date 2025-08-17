package com.handoff.model.dto.response;

import com.handoff.model.entity.User;
import com.handoff.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;

    public static AuthResponse from(User user, String token) {
        AuthResponse r = new AuthResponse();
        r.setToken(token);
        r.setUserId(user.getId());
        r.setEmail(user.getEmail() != null ? StringEscapeUtils.escapeHtml4(user.getEmail()) : null);
        r.setFirstName(user.getFirstName() != null ? StringEscapeUtils.escapeHtml4(user.getFirstName()) : null);
        r.setLastName(user.getLastName() != null ? StringEscapeUtils.escapeHtml4(user.getLastName()) : null);
        r.setRole(user.getRole());
        return r;
    }
}
