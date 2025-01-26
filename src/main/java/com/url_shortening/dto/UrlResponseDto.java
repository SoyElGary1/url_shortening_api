package com.url_shortening.dto;

import java.time.LocalDateTime;

public record UrlResponseDto(
        String id,
        String url,
        String shortUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
