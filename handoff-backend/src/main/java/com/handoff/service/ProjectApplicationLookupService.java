package com.handoff.service;

import com.handoff.exception.ResourceNotFoundException;
import com.handoff.model.entity.ProjectApplication;
import com.handoff.model.entity.User;
import com.handoff.repository.ProjectApplicationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectApplicationLookupService {
    private final ProjectApplicationRepository applicationRepository;
    private final UserService userService;

    public ProjectApplicationLookupService(ProjectApplicationRepository applicationRepository, UserService userService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
    }

    @Cacheable(value = "projectApplicationById", key = "#id")
    @Transactional(readOnly = true)
    public ProjectApplication getOrThrow(UUID id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
    }

    @Cacheable(value = "projectApplicationsByProject", key = "#projectId")
    @Transactional(readOnly = true)
    public List<ProjectApplication> listByProject(UUID projectId) {
        return applicationRepository.findByProjectId(projectId);
    }

    @Cacheable(value = "projectApplicationsByFinisher", key = "#finisherEmail")
    @Transactional(readOnly = true)
    public List<ProjectApplication> listMine(String finisherEmail) {
        User finisher = userService.findByEmailOrThrow(finisherEmail);
        return applicationRepository.findByFinisherId(finisher.getId());
    }
}

