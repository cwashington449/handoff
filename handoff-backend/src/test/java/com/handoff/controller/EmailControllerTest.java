package com.handoff.controller;

import com.handoff.security.JwtTokenProvider;
import com.handoff.service.EmailService;
import jakarta.mail.MessagingException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
@WithMockUser(username = "user@example.com")
class EmailControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    EmailService emailService;
    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void sendTestEmail_returnsOk() throws Exception {
        Mockito.doNothing().when(emailService).sendTemplateEmail(any(), any(), any(), any());
        String body = "{\"to\":\"test@example.com\",\"subject\":\"Test\",\"body\":\"Body\"}";
        mockMvc.perform(post("/api/v1/email/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void sendTestEmail_returns500OnException() throws Exception {
        Mockito.doThrow(new MessagingException("fail")).when(emailService).sendTemplateEmail(any(), any(), any(), any());
        String body = "{\"to\":\"test@example.com\",\"subject\":\"Test\",\"body\":\"Body\"}";
        mockMvc.perform(post("/api/v1/email/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void sendEmail_returnsOk() throws Exception {
        Mockito.doNothing().when(emailService).sendTemplateEmail(any(), any(), any(), any());
        String body = "{\"to\":\"test@example.com\",\"subject\":\"Test\",\"body\":\"Body\"}";
        mockMvc.perform(post("/api/v1/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void sendEmail_returns500OnException() throws Exception {
        Mockito.doThrow(new MessagingException("fail")).when(emailService).sendTemplateEmail(any(), any(), any(), any());
        String body = "{\"to\":\"test@example.com\",\"subject\":\"Test\",\"body\":\"Body\"}";
        mockMvc.perform(post("/api/v1/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .with(csrf()))
                .andExpect(status().isInternalServerError());
    }
}
