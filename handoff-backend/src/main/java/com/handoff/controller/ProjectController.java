package com.handoff.controller;

import com.handoff.model.dto.request.ProjectCreateRequest;
import com.handoff.model.dto.request.ProjectUpdateRequest;
import com.handoff.model.dto.response.ProjectResponse;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.model.enums.ProjectStatus;
import com.handoff.service.ProjectService;
import com.handoff.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectCreateRequest request,
                                                  Authentication authentication) {
        User creator = userService.findByEmailOrThrow(authentication.getName());
        Project p = projectService.create(
                creator,
                request.getTitle(),
                request.getDescription(),
                request.getRequirementsJson(),
                request.getBudgetMin(),
                request.getBudgetMax(),
                request.getBudgetCurrency(),
                request.getEstimatedTimeline(),
                request.getRequiredSkills(),
                request.getAttachmentsJson(),
                request.getApplicationDeadline()
        );
        return ResponseEntity.created(URI.create("/api/v1/projects/" + p.getId()))
                .body(ProjectResponse.from(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> get(@PathVariable UUID id) {
        Project p = projectService.getOrThrow(id);
        return ResponseEntity.ok(ProjectResponse.from(p));
    }

    @GetMapping("/mine")
    public ResponseEntity<List<ProjectResponse>> listMine(Authentication authentication) {
        User me = userService.findByEmailOrThrow(authentication.getName());
        List<ProjectResponse> responses = projectService.listMine(me.getId()).stream()
                .map(ProjectResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> listByStatus(@RequestParam(name = "status", required = false) ProjectStatus status) {
        List<Project> projects;
        if (status != null) {
            projects = projectService.listByStatus(status);
        } else {
            // default to a completed list if status omitted
            projects = projectService.listByStatus(ProjectStatus.COMPLETED);
        }
        List<ProjectResponse> responses = projects.stream().map(ProjectResponse::from).toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody ProjectUpdateRequest request,
                                                  Authentication authentication) {
        Project p = projectService.update(
                id,
                authentication.getName(),
                request.getTitle(),
                request.getDescription(),
                request.getRequirementsJson(),
                request.getBudgetMin(),
                request.getBudgetMax(),
                request.getBudgetCurrency(),
                request.getEstimatedTimeline(),
                request.getRequiredSkills(),
                request.getAttachmentsJson(),
                request.getApplicationDeadline()
        );
        return ResponseEntity.ok(ProjectResponse.from(p));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<ProjectResponse> publish(@PathVariable UUID id, Authentication authentication) {
        Project p = projectService.publish(id, authentication.getName());
        return ResponseEntity.ok(ProjectResponse.from(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, Authentication authentication) {
        projectService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementView(@PathVariable UUID id) {
        projectService.incrementViewCount(id);
        return ResponseEntity.accepted().build();
    }
}
