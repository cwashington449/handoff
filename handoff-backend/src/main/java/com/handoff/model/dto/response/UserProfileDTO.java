package com.handoff.model.dto.response;

import com.handoff.model.entity.User;
import com.handoff.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.util.UUID;

@Getter
@Setter
public class UserProfileDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;

    public static UserProfileDTO from(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.id = user.getId();
        dto.email = user.getEmail() != null ? StringEscapeUtils.escapeHtml4(user.getEmail()) : null;
        dto.firstName = user.getFirstName() != null ? StringEscapeUtils.escapeHtml4(user.getFirstName()) : null;
        dto.lastName = user.getLastName() != null ? StringEscapeUtils.escapeHtml4(user.getLastName()) : null;
        dto.role = user.getRole();
        return dto;
    }
}
