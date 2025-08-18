package com.handoff.controller;

import com.handoff.model.entity.Payment;
import com.handoff.security.JwtTokenProvider;
import com.handoff.service.PaymentService;
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

@WebMvcTest(PaymentController.class)
@WithMockUser(username = "user@example.com")
class PaymentControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    PaymentService paymentService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void create_returnsCreated() throws Exception {
        Payment p = new Payment();
        p.setId(UUID.randomUUID());
        Mockito.when(paymentService.create(any(), any(), any(), any(), any(), any())).thenReturn(p);
        String body = "{\"payeeId\":\"00000000-0000-0000-0000-000000000002\",\"amount\":100,\"currency\":\"USD\"}";
        mockMvc.perform(post("/api/v1/projects/00000000-0000-0000-0000-000000000001/payments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void list_returnsOk() throws Exception {
        Mockito.when(paymentService.listByProject(any(), any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/projects/00000000-0000-0000-0000-000000000001/payments"))
                .andExpect(status().isOk());
    }

    @Test
    void listByStatus_returnsOk() throws Exception {
        Mockito.when(paymentService.listByStatus(any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/payments/status/PENDING"))
                .andExpect(status().isOk());
    }

    @Test
    void update_returnsOk() throws Exception {
        Payment p = new Payment();
        p.setId(UUID.randomUUID());
        Mockito.when(paymentService.update(any(), any(), any(), any(), any())).thenReturn(p);
        String body = "{\"amount\":200,\"currency\":\"USD\"}";
        mockMvc.perform(put("/api/v1/payments/00000000-0000-0000-0000-000000000003")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void release_returnsOk() throws Exception {
        Payment p = new Payment();
        p.setId(UUID.randomUUID());
        Mockito.when(paymentService.release(any(), any())).thenReturn(p);
        mockMvc.perform(post("/api/v1/payments/00000000-0000-0000-0000-000000000003/release")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void refund_returnsOk() throws Exception {
        Payment p = new Payment();
        p.setId(UUID.randomUUID());
        Mockito.when(paymentService.refund(any(), any())).thenReturn(p);
        mockMvc.perform(post("/api/v1/payments/00000000-0000-0000-0000-000000000003/refund")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/payments/00000000-0000-0000-0000-000000000003")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}

