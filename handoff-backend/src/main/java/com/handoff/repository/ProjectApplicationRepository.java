package com.handoff.repository;

import com.handoff.model.entity.ProjectApplication;
import com.handoff.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, UUID> {
    List<ProjectApplication> findByProjectId(UUID projectId);
    List<ProjectApplication> findByFinisherId(UUID finisherId);
    long countByProjectIdAndStatus(UUID projectId, ApplicationStatus status);
    boolean existsByProjectIdAndFinisherId(UUID projectId, UUID finisherId);
}

