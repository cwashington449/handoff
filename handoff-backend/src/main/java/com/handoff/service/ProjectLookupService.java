package com.handoff.service;

import com.handoff.exception.ResourceNotFoundException;
import com.handoff.model.entity.Project;
import com.handoff.model.enums.ProjectStatus;
import com.handoff.repository.ProjectRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectLookupService {
    private final ProjectRepository projectRepository;

    public ProjectLookupService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Cacheable(value = "projectById", key = "#id")
    @Transactional(readOnly = true)
    public Project getOrThrow(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    @Cacheable(value = "projectsByCreator", key = "#creatorId")
    @Transactional(readOnly = true)
    public List<Project> listMine(UUID creatorId) {
        return projectRepository.findByCreatorId(creatorId);
    }

    @Cacheable(value = "projectsByStatus", key = "#status")
    @Transactional(readOnly = true)
    public List<Project> listByStatus(ProjectStatus status) {
        return projectRepository.findByStatus(status);
    }
}

