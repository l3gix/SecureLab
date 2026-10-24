package dev.securelab.minidocs.dto;

public record DocumentResponse(
        Long id,
        String title,
        String content
) {
}
