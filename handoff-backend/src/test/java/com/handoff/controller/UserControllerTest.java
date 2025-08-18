package com.handoff.controller;

import com.handoff.model.entity.User;
import com.handoff.security.JwtTokenProvider;
import com.handoff.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@WithMockUser(username = "user@example.com")
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    UserService userService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void profile_returnsOk() throws Exception {
        Mockito.when(userService.findByEmailOrThrow(any())).thenReturn(new User());
        mockMvc.perform(get("/api/v1/users/profile"))
                .andExpect(status().isOk());
    }

    @Test
    void updateProfile_returnsOk() throws Exception {
        Mockito.when(userService.updateProfile(any(), any(), any(), any(), any(), any())).thenReturn(new User());
        String body = "{\"firstName\":\"A\",\"lastName\":\"B\"}";
        mockMvc.perform(put("/api/v1/users/profile")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void deactivate_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/users/profile")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}

