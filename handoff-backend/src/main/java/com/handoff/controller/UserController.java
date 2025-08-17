package com.handoff.controller;

import com.handoff.model.dto.response.UserProfileDTO;
import com.handoff.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}