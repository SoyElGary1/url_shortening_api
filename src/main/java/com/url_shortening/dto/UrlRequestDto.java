package com.url_shortening.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UrlRequestDto(
        @NotBlank
        @Pattern(regexp = "^(https?://)?(localhost|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,})(:\\d+)?(/[^\\s]*)?$",
                message = "URL inválida"
        )
        String url
) {
}
