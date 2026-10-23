package dev.securelab.dto;

import dev.securelab.model.ScanStatus;

import java.time.Instant;

public record ScanResponse(
        Long id,
        String targetKey,
        ScanStatus status,
        Instant createdAt,
        Instant startedAt,
        Instant finishedAt

) {
}
