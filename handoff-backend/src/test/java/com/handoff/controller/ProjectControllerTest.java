package com.handoff.controller;

import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.security.JwtTokenProvider;
import com.handoff.service.ProjectService;
import com.handoff.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@WithMockUser(username = "user@example.com")
class ProjectControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    ProjectService projectService;
    @MockitoBean
    UserService userService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void create_returnsCreated() throws Exception {
        Project p = new Project();
        p.setId(UUID.randomUUID());
        Mockito.when(userService.findByEmailOrThrow(any())).thenReturn(new User());
        Mockito.when(projectService.create(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(p);
        String body = "{\"title\":\"t\",\"description\":\"d\",\"requirementsJson\":{},\"budgetCurrency\":\"USD\"}";
        mockMvc.perform(post("/api/v1/projects")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void get_returnsOk() throws Exception {
        Project p = new Project();
        p.setId(UUID.randomUUID());
        Mockito.when(projectService.getOrThrow(any())).thenReturn(p);
        mockMvc.perform(get("/api/v1/projects/00000000-0000-0000-0000-000000000001"))
                .andExpect(status().isOk());
    }

    @Test
    void listMine_returnsOk() throws Exception {
        Mockito.when(userService.findByEmailOrThrow(any())).thenReturn(new User());
        Mockito.when(projectService.listMine(any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/projects/mine"))
                .andExpect(status().isOk());
    }

    @Test
    void listByStatus_returnsOk() throws Exception {
        Mockito.when(projectService.listByStatus(any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/projects/status/OPEN"))
                .andExpect(status().isOk());
    }

    @Test
    void update_returnsOk() throws Exception {
        Project p = new Project();
        p.setId(UUID.randomUUID());
        Mockito.when(projectService.update(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(p);
        String body = "{\"title\":\"t\",\"description\":\"d\",\"requirementsJson\":{},\"budgetCurrency\":\"USD\"}";
        mockMvc.perform(put("/api/v1/projects/00000000-0000-0000-0000-000000000001")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void publish_returnsOk() throws Exception {
        Project p = new Project();
        p.setId(UUID.randomUUID());
        Mockito.when(projectService.publish(any(), any())).thenReturn(p);
        mockMvc.perform(post("/api/v1/projects/00000000-0000-0000-0000-000000000001/publish")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/projects/00000000-0000-0000-0000-000000000001")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void incrementViewCount_returnsNoContent() throws Exception {
        mockMvc.perform(post("/api/v1/projects/00000000-0000-0000-0000-000000000001/view")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}

