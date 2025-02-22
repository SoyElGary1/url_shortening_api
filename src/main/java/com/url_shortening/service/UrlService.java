package com.url_shortening.service;

import com.url_shortening.dto.UrlRequestDto;
import com.url_shortening.dto.UrlResponseDto;
import com.url_shortening.dto.UrlStatsResponseDto;
import com.url_shortening.mapper.UrlMapper;
import com.url_shortening.model.Url;
import com.url_shortening.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UrlResponseDto saveUrl(UrlRequestDto url) {
        if (url.url() == null || url.url().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        String shortUrlId;
        do {
            shortUrlId = UUID.randomUUID().toString().substring(0, 8);
        } while (repository.existsByShortUrl(shortUrlId));
        Url entity = UrlMapper.toEntity(url);
        entity.setShortUrl(shortUrlId);
        entity.setAccessCount(0);
        repository.save(entity);
        return UrlMapper.toResponseDto(entity);
    }

    @Transactional
    public UrlResponseDto getUrl(String shortUrl) {
        Url newUrl = repository.getReferenceByShortUrl(shortUrl);
        accessCount(newUrl);

        return UrlMapper.toResponseDto(newUrl);
    }

    @Transactional
    public UrlStatsResponseDto getUrlStats(String shortUrl) {
        Url newUrl = repository.getReferenceByShortUrl(shortUrl);
        return UrlMapper.toStatsResponseDto(newUrl);
    }

    @Transactional
    public UrlResponseDto updateUrl(String shortUrl, UrlRequestDto newUrl) {
        Url url = repository.getReferenceByShortUrl(shortUrl);
        if (newUrl != null) {
            url.setUrl(newUrl.url());
        }
        return UrlMapper.toResponseDto(url);
    }

    @Transactional
    public void deleteUrl(String shortUrl) {
        Url url = repository.getReferenceByShortUrl(shortUrl);
        repository.delete(url);
    }

    public void accessCount(Url url) {
        if (url.getShortUrl() != null) {
            url.setAccessCount(url.getAccessCount() + 1);
        }
    }
}
