package com.handoff.service;

import com.handoff.exception.ForbiddenException;
import com.handoff.exception.ResourceNotFoundException;
import com.handoff.model.entity.Message;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.repository.MessageRepository;
import com.handoff.repository.ProjectApplicationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MessageLookupService {
    private final MessageRepository messageRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectApplicationRepository applicationRepository;

    public MessageLookupService(MessageRepository messageRepository, ProjectService projectService, UserService userService, ProjectApplicationRepository applicationRepository) {
        this.messageRepository = messageRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.applicationRepository = applicationRepository;
    }

    private boolean canAccessProject(Project project, User user) {
        return (project.getCreator() != null && project.getCreator().getId().equals(user.getId()))
                || applicationRepository.existsByProjectIdAndFinisherId(project.getId(), user.getId());
    }

    private void checkProjectAccess(Project project, User user, String errorMsg) {
        if (!canAccessProject(project, user)) {
            throw new ForbiddenException(errorMsg);
        }
    }

    @Cacheable(value = "messagesByProject", key = "#projectId + ':' + #requesterEmail")
    @Transactional(readOnly = true)
    public List<Message> listByProject(UUID projectId, String requesterEmail) {
        Project project = projectService.getOrThrow(projectId);
        User requester = userService.findByEmailOrThrow(requesterEmail);
        checkProjectAccess(project, requester, "Not allowed to view messages for this project");
        return messageRepository.findByProjectId(projectId);
    }

    @Cacheable(value = "messageById", key = "#id + ':' + #requesterEmail")
    @Transactional(readOnly = true)
    public Message getOrThrow(UUID id, String requesterEmail) {
        Message m = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        Project project = m.getProject();
        User requester = userService.findByEmailOrThrow(requesterEmail);
        checkProjectAccess(project, requester, "Not allowed to view this message");
        return m;
    }
}

