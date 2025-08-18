package com.handoff.controller;

import com.handoff.model.entity.ProjectApplication;
import com.handoff.security.JwtTokenProvider;
import com.handoff.service.ProjectApplicationService;
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

@WebMvcTest(ProjectApplicationController.class)
@WithMockUser(username = "user@example.com")
class ProjectApplicationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    ProjectApplicationService applicationService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void submit_returnsCreated() throws Exception {
        ProjectApplication app = new ProjectApplication();
        app.setId(UUID.randomUUID());
        Mockito.when(applicationService.submit(any(), any(), any(), any(), any(), any())).thenReturn(app);
        String body = "{\"coverLetter\":\"hi\"}";
        mockMvc.perform(post("/api/v1/projects/00000000-0000-0000-0000-000000000001/applications")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void listForProject_returnsOk() throws Exception {
        Mockito.when(applicationService.listByProject(any(), any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/projects/00000000-0000-0000-0000-000000000001/applications"))
                .andExpect(status().isOk());
    }

    @Test
    void listMine_returnsOk() throws Exception {
        Mockito.when(applicationService.listMine(any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/applications/mine"))
                .andExpect(status().isOk());
    }

    @Test
    void updateStatus_returnsOk() throws Exception {
        ProjectApplication app = new ProjectApplication();
        app.setId(UUID.randomUUID());
        Mockito.when(applicationService.updateStatus(any(), any(), any())).thenReturn(app);
        String body = "{\"status\":\"ACCEPTED\"}";
        mockMvc.perform(patch("/api/v1/applications/00000000-0000-0000-0000-000000000002/status")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/applications/00000000-0000-0000-0000-000000000002")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}

