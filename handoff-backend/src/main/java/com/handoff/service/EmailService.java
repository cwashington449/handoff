package com.handoff.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Service for sending emails, including plain HTML emails and template-based emails.
 * Uses JavaMailSender for email delivery and Thymeleaf for template processing.
 */
@Service
@RequiredArgsConstructor
public class EmailService {
    // Constant for the default sender email address
    private static final String FROM_ADDRESS = "no-reply@handoff-mvp.com";

    private final JavaMailSender mailSender; // JavaMailSender instance for sending emails
    private final TemplateEngine templateEngine; // Thymeleaf TemplateEngine for processing email templates

    /**
     * Sends an email with the specified recipient, subject, and HTML body content.
     *
     * @param to       The recipient's email address.
     * @param subject  The subject of the email.
     * @param bodyHtml The HTML content of the email body.
     * @throws MessagingException If an error occurs while creating or sending the email.
     */
    public void sendEmail(String to, String subject, String bodyHtml) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(FROM_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(bodyHtml, true);
        mailSender.send(message);
    }

    /**
     * Sends an email using a Thymeleaf template.
     *
     * @param to           The recipient's email address.
     * @param subject      The subject of the email.
     * @param templateName The name of the Thymeleaf template to use.
     * @param variables    A map of variables to be passed to the template.
     * @throws MessagingException If an error occurs while creating or sending the email.
     */
    public void sendTemplateEmail(String to, String subject, String templateName, java.util.Map<String, Object> variables) throws MessagingException {
        Context context = new Context();
        context.setVariables(variables);
        String html = templateEngine.process(templateName, context);
        sendEmail(to, subject, html);
    }
}