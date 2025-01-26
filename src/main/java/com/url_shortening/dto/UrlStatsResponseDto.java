package com.url_shortening.dto;

import java.time.LocalDateTime;

public record UrlStatsResponseDto(
        Long id,
        String url,
        String shortUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer accessCount
) {
}
