package com.handoff.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class ControllerUtils {
    private ControllerUtils() {
    }

    public static URI buildLocation(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}