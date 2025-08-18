package com.handoff.repository;

import com.handoff.model.entity.Project;
import com.handoff.model.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByCreatorId(UUID creatorId);
    List<Project> findByStatus(ProjectStatus status);
    long countByCreatorId(UUID creatorId);
}

