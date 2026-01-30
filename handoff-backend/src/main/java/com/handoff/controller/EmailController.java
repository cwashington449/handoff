package com.handoff.controller;

import com.handoff.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @Profile({"local", "nonprod"})
    @PostMapping("/test")
    public ResponseEntity<?> sendTestEmail(@RequestBody EmailTestRequest request) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("subject", request.getSubject());
        variables.put("body", request.getBody());
        try {
            emailService.sendTemplateEmail(request.getTo(), request.getSubject(), "test-email", variables);
            return ResponseEntity.ok("Test email sent to " + request.getTo());
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailTestRequest request) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("subject", request.getSubject());
        variables.put("body", request.getBody());
        try {
            emailService.sendTemplateEmail(request.getTo(), request.getSubject(), "prod-email", variables);
            return ResponseEntity.ok("Email sent to " + request.getTo());
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }

    @Data
    public static class EmailTestRequest {
        private String to;
        private String subject;
        private String body;
    }
}
