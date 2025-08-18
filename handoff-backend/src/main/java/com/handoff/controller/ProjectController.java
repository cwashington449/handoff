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

import java.util.List;
import java.util.UUID;

import static com.handoff.util.ControllerUtils.buildLocation;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectCreateRequest request,
                                                  Authentication authentication) {
        request.sanitize();
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
        return ResponseEntity.created(buildLocation(project.getId()))
                .body(ProjectResponse.from(project));
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProjectResponse>> listByStatus(@PathVariable ProjectStatus status) {
        List<ProjectResponse> responses = projectService.listByStatus(status).stream()
                .map(ProjectResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody ProjectUpdateRequest request,
                                                  Authentication authentication) {
        request.sanitize();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, Authentication authentication) {
        projectService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable UUID id) {
        projectService.incrementViewCount(id);
        return ResponseEntity.noContent().build();
    }
}
