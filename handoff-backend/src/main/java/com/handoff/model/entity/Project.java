package com.handoff.model.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.handoff.model.enums.ProjectComplexity;
import com.handoff.model.enums.ProjectStatus;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "projects", indexes = {
        @Index(name = "idx_projects_creator", columnList = "creator_id"),
        @Index(name = "idx_projects_status", columnList = "status"),
        @Index(name = "idx_projects_published", columnList = "published_at")
})
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false, length = 255)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Type(JsonType.class)
    @Column(name = "requirements", nullable = false, columnDefinition = "jsonb")
    private JsonNode requirementsJson;

    @Column(name = "budget_min", precision = 10, scale = 2)
    private BigDecimal budgetMin;

    @Column(name = "budget_max", precision = 10, scale = 2)
    private BigDecimal budgetMax;

    @Column(name = "budget_currency", length = 3)
    private String budgetCurrency = "USD";

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProjectComplexity complexity;

    @Column(name = "estimated_timeline", length = 100)
    private String estimatedTimeline;

    @ElementCollection
    @CollectionTable(name = "project_required_skills", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "skill", length = 100)
    private Set<String> requiredSkills = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProjectStatus status = ProjectStatus.DRAFT;

    @Column(name = "published_at")
    private Instant publishedAt;

    @Column(name = "application_deadline")
    private Instant applicationDeadline;

    @Type(JsonType.class)
    @Column(name = "attachments", columnDefinition = "jsonb")
    private JsonNode attachmentsJson;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "application_count")
    private Integer applicationCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ProjectApplication> applications = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Message> messages = new ArrayList<>();
}
