package com.handoff.model.dto.response;

import com.handoff.model.entity.User;
import com.handoff.model.enums.UserRole;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserProfileDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;

    public static UserProfileDTO from(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.role = user.getRole();
        return dto;
    }
}
