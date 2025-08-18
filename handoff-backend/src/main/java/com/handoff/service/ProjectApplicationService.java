package com.handoff.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.exception.BadRequestException;
import com.handoff.exception.ForbiddenException;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.ProjectApplication;
import com.handoff.model.entity.User;
import com.handoff.model.enums.ApplicationStatus;
import com.handoff.model.enums.ProjectStatus;
import com.handoff.repository.ProjectApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectApplicationService {

    private final ProjectApplicationRepository applicationRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectApplicationLookupService projectApplicationLookupService;

    @Transactional
    public ProjectApplication submit(UUID projectId, String finisherEmail, String coverLetter, BigDecimal bidAmount,
                                     String proposedTimeline, JsonNode attachmentsJson) {
        Project project = projectService.getOrThrow(projectId);
        validateProjectForApplication(project);
        User finisher = getUserOrThrow(finisherEmail);
        validateDuplicateApplication(projectId, finisher.getId());

        ProjectApplication app = new ProjectApplication();
        app.setProject(project);
        app.setFinisher(finisher);
        app.setStatus(ApplicationStatus.SUBMITTED);
        app.setCoverLetter(coverLetter);
        app.setBidAmount(bidAmount);
        app.setProposedTimeline(proposedTimeline);
        app.setAttachmentsJson(attachmentsJson);

        incrementApplicationCount(project);
        return applicationRepository.save(app);
    }

    public ProjectApplication getOrThrow(UUID id) {
        return projectApplicationLookupService.getOrThrow(id);
    }

    public List<ProjectApplication> listByProject(UUID projectId, String requesterEmail) {
        Project project = projectService.getOrThrow(projectId);
        User requester = getUserOrThrow(requesterEmail);
        requireCreator(project, requester, "Only the project creator can view applications");
        return projectApplicationLookupService.listByProject(projectId);
    }

    public List<ProjectApplication> listMine(String finisherEmail) {
        return projectApplicationLookupService.listMine(finisherEmail);
    }

    @Transactional
    public ProjectApplication updateStatus(UUID applicationId, String requesterEmail, ApplicationStatus status) {
        ProjectApplication app = getOrThrow(applicationId);
        User requester = getUserOrThrow(requesterEmail);
        validateAndSetStatus(app, requester, status);
        return applicationRepository.save(app);
    }

    @Transactional
    public void delete(UUID applicationId, String requesterEmail) {
        ProjectApplication app = getOrThrow(applicationId);
        User requester = getUserOrThrow(requesterEmail);
        requireOwnerOrCreator(app, requester, "Not allowed to delete this application");
        applicationRepository.delete(app);
    }

    private void validateProjectForApplication(Project project) {
        if (project.getStatus() != ProjectStatus.OPEN) {
            throw new BadRequestException("Project is not open for applications");
        }
        if (project.getApplicationDeadline() != null && Instant.now().isAfter(project.getApplicationDeadline())) {
            throw new BadRequestException("Application deadline has passed");
        }
    }

    private void validateDuplicateApplication(UUID projectId, UUID finisherId) {
        if (applicationRepository.existsByProjectIdAndFinisherId(projectId, finisherId)) {
            throw new BadRequestException("You have already applied to this project");
        }
    }

    private void incrementApplicationCount(Project project) {
        int count = project.getApplicationCount() == null ? 0 : project.getApplicationCount();
        project.setApplicationCount(count + 1);
    }

    private void validateAndSetStatus(ProjectApplication app, User requester, ApplicationStatus status) {
        if (status == ApplicationStatus.SUBMITTED) {
            throw new BadRequestException("Cannot revert to SUBMITTED");
        }
        boolean creatorAction = status == ApplicationStatus.REVIEWED
                || status == ApplicationStatus.ACCEPTED
                || status == ApplicationStatus.REJECTED;

        if (creatorAction) {
            requireCreator(app.getProject(), requester, "Only the project creator can update this status");
        } else {
            if (isNotOwner(app, requester) || app.getStatus() == ApplicationStatus.ACCEPTED) {
                throw new BadRequestException("Invalid action for the applicant");
            }
        }
        app.setStatus(status);
    }

    private User getUserOrThrow(String email) {
        return userService.findByEmailOrThrow(email);
    }

    private boolean isNotCreator(Project project, User user) {
        return project.getCreator() == null || !project.getCreator().getId().equals(user.getId());
    }

    private boolean isNotOwner(ProjectApplication app, User user) {
        return app.getFinisher() == null || !app.getFinisher().getId().equals(user.getId());
    }

    private void requireCreator(Project project, User user, String message) {
        if (isNotCreator(project, user)) {
            throw new ForbiddenException(message);
        }
    }

    private void requireOwnerOrCreator(ProjectApplication app, User user, String message) {
        if (isNotOwner(app, user) && isNotCreator(app.getProject(), user)) {
            throw new ForbiddenException(message);
        }
    }
}