package com.url_shortening.mapper;

import com.url_shortening.dto.UrlRequestDto;
import com.url_shortening.dto.UrlResponseDto;
import com.url_shortening.dto.UrlStatsResponseDto;
import com.url_shortening.model.Url;

public class UrlMapper {

    public static UrlResponseDto toResponseDto(Url url) {
        return new UrlResponseDto(
                url.getId(),
                url.getUrl(),
                url.getShortUrl(),
                url.getCreatedAt(),
                url.getUpdateAt()
        );
    }

    public static UrlStatsResponseDto toStatsResponseDto(Url url) {
        return new UrlStatsResponseDto(
                url.getId(),
                url.getUrl(),
                url.getShortUrl(),
                url.getCreatedAt(),
                url.getUpdateAt(),
                url.getAccessCount()
        );
    }

    public static Url toEntity(UrlRequestDto requestDto) {
        Url url = new Url();
        url.setUrl(requestDto.url());
        return url;
    }

}
