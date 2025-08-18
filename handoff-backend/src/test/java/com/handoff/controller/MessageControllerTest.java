package com.handoff.controller;

import com.handoff.model.entity.Message;
import com.handoff.security.JwtTokenProvider;
import com.handoff.service.MessageService;
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

@WebMvcTest(MessageController.class)
@WithMockUser(username = "user@example.com")
class MessageControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    MessageService messageService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void send_returnsCreated() throws Exception {
        Message m = new Message();
        m.setId(UUID.randomUUID());
        Mockito.when(messageService.send(any(), any(), any(), any())).thenReturn(m);
        String body = "{\"content\":\"hi\"}";
        mockMvc.perform(post("/api/v1/projects/00000000-0000-0000-0000-000000000001/messages")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void list_returnsOk() throws Exception {
        Mockito.when(messageService.listByProject(any(), any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/projects/00000000-0000-0000-0000-000000000001/messages"))
                .andExpect(status().isOk());
    }

    @Test
    void update_returnsOk() throws Exception {
        Message m = new Message();
        m.setId(UUID.randomUUID());
        Mockito.when(messageService.update(any(), any(), any(), any(), any())).thenReturn(m);
        String body = "{\"content\":\"edit\"}";
        mockMvc.perform(put("/api/v1/projects/00000000-0000-0000-0000-000000000001/messages/00000000-0000-0000-0000-000000000002")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/projects/00000000-0000-0000-0000-000000000001/messages/00000000-0000-0000-0000-000000000002")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}

