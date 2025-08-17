package com.handoff.controller;

import com.handoff.model.dto.request.UserUpdateRequest;
import com.handoff.model.dto.response.UserProfileDTO;
import com.handoff.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> profile(Authentication authentication) {
        return ResponseEntity.ok(
                UserProfileDTO.from(userService.findByEmailOrThrow(authentication.getName()))
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDTO> updateProfile(@Valid @RequestBody UserUpdateRequest request,
                                                        Authentication authentication) {
        request.sanitize();
        var updated = userService.updateProfile(
                authentication.getName(),
                request.getFirstName(),
                request.getLastName(),
                request.getProfileJson(),
                request.getPreferencesJson(),
                request.getSkills()
        );
        return ResponseEntity.ok(UserProfileDTO.from(updated));
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> deactivate(Authentication authentication) {
        userService.deactivate(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}