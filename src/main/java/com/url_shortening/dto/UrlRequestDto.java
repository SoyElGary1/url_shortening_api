package com.url_shortening.dto;

import jakarta.validation.constraints.NotBlank;

public record UrlRequestDto(
        @NotBlank
        String url
) {
}
