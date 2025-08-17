package com.handoff.controller;

import com.handoff.model.dto.request.ProjectCreateRequest;
import com.handoff.model.dto.request.ProjectUpdateRequest;
import com.handoff.model.dto.response.ProjectResponse;
import com.handoff.model.entity.Project;
import com.handoff.model.entity.User;
import com.handoff.service.ProjectService;
import com.handoff.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
        Project project = projectService.create(
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
        return ResponseEntity.created(buildProjectLocation(project.getId()))
                .body(ProjectResponse.from(sanitizeProject(project)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> get(@PathVariable UUID id) {
        Project project = projectService.getOrThrow(id);
        return ResponseEntity.ok(ProjectResponse.from(project));
    }

    @GetMapping("/mine")
    public ResponseEntity<List<ProjectResponse>> listMine(Authentication authentication) {
        User me = userService.findByEmailOrThrow(authentication.getName());
        List<ProjectResponse> responses = projectService.listMine(me.getId()).stream()
                .map(ProjectResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody ProjectUpdateRequest request,
                                                  Authentication authentication) {
        Project project = projectService.update(
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
        return ResponseEntity.ok(ProjectResponse.from(project));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<ProjectResponse> publish(@PathVariable UUID id, Authentication authentication) {
        Project project = projectService.publish(id, authentication.getName());
        return ResponseEntity.ok(ProjectResponse.from(project));
    }

    private URI buildProjectLocation(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    private Project sanitizeProject(Project project) {
        // Example: sanitize fields to prevent XSS, e.g., using Apache Commons Text
        project.setTitle(StringEscapeUtils.escapeHtml4(project.getTitle()));
        project.setDescription(StringEscapeUtils.escapeHtml4(project.getDescription()));
        // Sanitize other fields as needed
        return project;
    }
}
