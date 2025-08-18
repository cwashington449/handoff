package com.handoff.controller;

import com.handoff.model.dto.request.ProjectApplicationCreateRequest;
import com.handoff.model.dto.request.ProjectApplicationStatusUpdateRequest;
import com.handoff.model.dto.response.ProjectApplicationResponse;
import com.handoff.model.entity.ProjectApplication;
import com.handoff.service.ProjectApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.handoff.util.ControllerUtils.buildLocation;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProjectApplicationController {

    private final ProjectApplicationService applicationService;

    @PostMapping("/projects/{projectId}/applications")
    public ResponseEntity<ProjectApplicationResponse> submit(@PathVariable UUID projectId,
                                                             @Valid @RequestBody ProjectApplicationCreateRequest request,
                                                             Authentication authentication) {
        request.sanitize();
        ProjectApplication app = applicationService.submit(
                projectId,
                authentication.getName(),
                request.getCoverLetter(),
                request.getBidAmount(),
                request.getProposedTimeline(),
                request.getAttachmentsJson()
        );
        return ResponseEntity.created(buildLocation(app.getId()))
                .body(ProjectApplicationResponse.from(app));
    }

    @GetMapping("/projects/{projectId}/applications")
    public ResponseEntity<List<ProjectApplicationResponse>> listForProject(@PathVariable UUID projectId,
                                                                           Authentication authentication) {
        List<ProjectApplicationResponse> list = applicationService
                .listByProject(projectId, authentication.getName())
                .stream().map(ProjectApplicationResponse::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/applications/mine")
    public ResponseEntity<List<ProjectApplicationResponse>> listMine(Authentication authentication) {
        List<ProjectApplicationResponse> list = applicationService
                .listMine(authentication.getName())
                .stream().map(ProjectApplicationResponse::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/applications/{applicationId}/status")
    public ResponseEntity<ProjectApplicationResponse> updateStatus(@PathVariable UUID applicationId,
                                                                   @Valid @RequestBody ProjectApplicationStatusUpdateRequest request,
                                                                   Authentication authentication) {
        request.sanitize();
        ProjectApplication updated = applicationService.updateStatus(applicationId, authentication.getName(), request.getStatus());
        return ResponseEntity.ok(ProjectApplicationResponse.from(updated));
    }

    @DeleteMapping("/applications/{applicationId}")
    public ResponseEntity<Void> delete(@PathVariable UUID applicationId, Authentication authentication) {
        applicationService.delete(applicationId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
