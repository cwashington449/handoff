package com.handoff.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.exception.ForbiddenException;
import com.handoff.model.entity.Message;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.repository.MessageRepository;
import com.handoff.repository.ProjectApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectApplicationRepository applicationRepository;
    private final MessageLookupService messageLookupService;

    private boolean canAccessProject(Project project, User user) {
        return (project.getCreator() != null && project.getCreator().getId().equals(user.getId()))
                || applicationRepository.existsByProjectIdAndFinisherId(project.getId(), user.getId());
    }

    private void checkProjectAccess(Project project, User user, String errorMsg) {
        if (!canAccessProject(project, user)) {
            throw new ForbiddenException(errorMsg);
        }
    }

    @Transactional
    public Message send(UUID projectId, String senderEmail, String content, JsonNode attachmentsJson) {
        Project project = projectService.getOrThrow(projectId);
        User sender = userService.findByEmailOrThrow(senderEmail);
        checkProjectAccess(project, sender, "Not allowed to message on this project");
        Message m = new Message();
        m.setProject(project);
        m.setSender(sender);
        m.setContent(content);
        m.setAttachmentsJson(attachmentsJson);
        return messageRepository.save(m);
    }

    // Delegate to lookup service
    public List<Message> listByProject(UUID projectId, String requesterEmail) {
        return messageLookupService.listByProject(projectId, requesterEmail);
    }

    public Message getOrThrow(UUID id, String requesterEmail) {
        return messageLookupService.getOrThrow(id, requesterEmail);
    }

    @Transactional
    public Message update(UUID projectId, UUID messageId, String requesterEmail, String content, JsonNode attachmentsJson) {
        Message m = getOrThrow(messageId, requesterEmail);
        if (!m.getProject().getId().equals(projectId)) {
            throw new ForbiddenException("Message does not belong to this project");
        }
        User requester = userService.findByEmailOrThrow(requesterEmail);
        if (m.getSender() == null || !m.getSender().getId().equals(requester.getId())) {
            throw new ForbiddenException("Only the sender can update the message");
        }
        if (content != null) m.setContent(content);
        if (attachmentsJson != null) m.setAttachmentsJson(attachmentsJson);
        return messageRepository.save(m);
    }

    @Transactional
    public void delete(UUID projectId, UUID messageId, String requesterEmail) {
        Message m = getOrThrow(messageId, requesterEmail);
        if (!m.getProject().getId().equals(projectId)) {
            throw new ForbiddenException("Message does not belong to this project");
        }
        User requester = userService.findByEmailOrThrow(requesterEmail);
        boolean isSender = m.getSender() != null && m.getSender().getId().equals(requester.getId());
        boolean isCreator = m.getProject().getCreator() != null && m.getProject().getCreator().getId().equals(requester.getId());
        if (!isSender && !isCreator) {
            throw new ForbiddenException("Not allowed to delete this message");
        }
        messageRepository.delete(m);
    }
}