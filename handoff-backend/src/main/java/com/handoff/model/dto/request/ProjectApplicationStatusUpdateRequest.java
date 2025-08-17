package com.handoff.model.dto.request;

import com.handoff.model.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectApplicationStatusUpdateRequest {
    @NotNull
    private ApplicationStatus status;

    public void sanitize() {
        // nothing to sanitize for enum-based requests
    }
}
