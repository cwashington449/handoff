package com.handoff.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * Sends an email using a Thymeleaf template for the body.
     *
     * @param to       recipient email
     * @param subject  subject line
     * @param bodyHtml HTML body (can be a Thymeleaf template string)
     * @throws MessagingException if sending fails
     */
    public void sendEmail(String to, String subject, String bodyHtml) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(bodyHtml, true);
        mailSender.send(message);
    }

    /**
     * Renders a Thymeleaf template with variables and sends the email.
     *
     * @param to           recipient
     * @param subject      subject
     * @param templateName Thymeleaf template name (in resources/templates)
     * @param variables    context variables
     * @throws MessagingException if sending fails
     */
    public void sendTemplateEmail(String to, String subject, String templateName, java.util.Map<String, Object> variables) throws MessagingException {
        Context context = new Context();
        context.setVariables(variables);
        String html = templateEngine.process(templateName, context);
        sendEmail(to, subject, html);
    }
}

