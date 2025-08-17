package com.handoff.model.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.model.enums.ApplicationStatus;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "project_applications", indexes = {
        @Index(name = "idx_app_project", columnList = "project_id"),
        @Index(name = "idx_app_finisher", columnList = "finisher_id"),
        @Index(name = "idx_app_status", columnList = "status")
})
@Getter
@Setter
public class ProjectApplication {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "finisher_id", nullable = false)
    private User finisher;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "bid_amount", precision = 10, scale = 2)
    private BigDecimal bidAmount;

    @Column(name = "proposed_timeline", length = 100)
    private String proposedTimeline;

    @Type(JsonType.class)
    @Column(name = "attachments", columnDefinition = "jsonb")
    private JsonNode attachmentsJson;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
