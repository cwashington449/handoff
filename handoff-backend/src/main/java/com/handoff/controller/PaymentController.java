package com.handoff.controller;

import com.handoff.model.dto.request.PaymentCreateRequest;
import com.handoff.model.dto.request.PaymentUpdateRequest;
import com.handoff.model.dto.response.PaymentResponse;
import com.handoff.model.entity.Payment;
import com.handoff.model.enums.PaymentStatus;
import com.handoff.service.PaymentService;
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
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/projects/{projectId}/payments")
    public ResponseEntity<PaymentResponse> create(@PathVariable UUID projectId,
                                                  @Valid @RequestBody PaymentCreateRequest request,
                                                  Authentication authentication) {
        request.sanitize();
        Payment p = paymentService.create(
                projectId,
                authentication.getName(),
                request.getPayeeId(),
                request.getAmount(),
                request.getCurrency(),
                request.getMetadataJson()
        );
        return ResponseEntity.created(buildLocation(p.getId()))
                .body(PaymentResponse.from(p));
    }

    @GetMapping("/projects/{projectId}/payments")
    public ResponseEntity<List<PaymentResponse>> list(@PathVariable UUID projectId,
                                                      Authentication authentication) {
        List<PaymentResponse> list = paymentService.listByProject(projectId, authentication.getName())
                .stream().map(PaymentResponse::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/payments/status/{status}")
    public ResponseEntity<List<PaymentResponse>> listByStatus(@PathVariable PaymentStatus status) {
        List<PaymentResponse> list = paymentService.listByStatus(status)
                .stream().map(PaymentResponse::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentResponse> update(@PathVariable UUID paymentId,
                                                  @Valid @RequestBody PaymentUpdateRequest request,
                                                  Authentication authentication) {
        request.sanitize();
        Payment p = paymentService.update(paymentId, authentication.getName(), request.getAmount(), request.getCurrency(), request.getMetadataJson());
        return ResponseEntity.ok(PaymentResponse.from(p));
    }

    @PostMapping("/payments/{paymentId}/release")
    public ResponseEntity<PaymentResponse> release(@PathVariable UUID paymentId, Authentication authentication) {
        Payment p = paymentService.release(paymentId, authentication.getName());
        return ResponseEntity.ok(PaymentResponse.from(p));
    }

    @PostMapping("/payments/{paymentId}/refund")
    public ResponseEntity<PaymentResponse> refund(@PathVariable UUID paymentId, Authentication authentication) {
        Payment p = paymentService.refund(paymentId, authentication.getName());
        return ResponseEntity.ok(PaymentResponse.from(p));
    }

    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID paymentId, Authentication authentication) {
        paymentService.delete(paymentId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
