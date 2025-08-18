package com.handoff.controller;

import com.handoff.security.JwtTokenProvider;
import com.handoff.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@WithMockUser(username = "user@example.com")
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    UserService userService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;
    @MockitoBean
    AuthenticationManager authenticationManager;

    @Test
    @DisplayName("register returns 400 if email exists")
    void register_emailExists() throws Exception {
        Mockito.when(userService.existsByEmail(eq("test@example.com"))).thenReturn(true);
        String body = "{" +
                "\"email\":\"test@example.com\"," +
                "\"password\":\"pw\"," +
                "\"firstName\":\"A\"," +
                "\"lastName\":\"B\"," +
                "\"role\":\"CREATOR\"}";
        mockMvc.perform(post("/api/v1/auth/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("login returns 401 on bad credentials")
    void login_badCredentials() throws Exception {
        Mockito.doThrow(new BadCredentialsException("bad creds")).when(authenticationManager)
                .authenticate(any());
        String body = "{" +
                "\"email\":\"test@example.com\"," +
                "\"password\":\"pw\"}";
        mockMvc.perform(post("/api/v1/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }
}

