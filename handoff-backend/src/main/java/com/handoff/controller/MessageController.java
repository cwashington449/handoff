package com.handoff.controller;

import com.handoff.model.dto.request.MessageCreateRequest;
import com.handoff.model.dto.request.MessageUpdateRequest;
import com.handoff.model.dto.response.MessageResponse;
import com.handoff.model.entity.Message;
import com.handoff.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.handoff.util.ControllerUtils.buildLocation;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> send(@PathVariable UUID projectId,
                                                @Valid @RequestBody MessageCreateRequest request,
                                                Authentication authentication) {
        request.sanitize();
        Message m = messageService.send(projectId, authentication.getName(), request.getContent(), request.getAttachmentsJson());
        return ResponseEntity.created(buildLocation(m.getId()))
                .body(MessageResponse.from(m));
    }

    @GetMapping
    public ResponseEntity<List<MessageResponse>> list(@PathVariable UUID projectId,
                                                      Authentication authentication) {
        List<MessageResponse> list = messageService.listByProject(projectId, authentication.getName())
                .stream().map(MessageResponse::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResponse> update(@PathVariable UUID projectId,
                                                  @PathVariable UUID messageId,
                                                  @Valid @RequestBody MessageUpdateRequest request,
                                                  Authentication authentication) {
        request.sanitize();
        Message m = messageService.update(projectId, messageId, authentication.getName(), request.getContent(), request.getAttachmentsJson());
        return ResponseEntity.ok(MessageResponse.from(m));
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> delete(@PathVariable UUID projectId,
                                       @PathVariable UUID messageId,
                                       Authentication authentication) {
        messageService.delete(projectId, messageId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
