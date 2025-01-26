package com.url_shortening.service;

import com.url_shortening.dto.UrlRequestDto;
import com.url_shortening.dto.UrlResponseDto;
import com.url_shortening.dto.UrlStatsResponseDto;
import com.url_shortening.mapper.UrlMapper;
import com.url_shortening.model.Url;
import com.url_shortening.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UrlResponseDto saveUrl(UrlRequestDto url) {
        Url entity = UrlMapper.toEntity(url);
        Random random = new Random();
        random.longs(5L);
        String randomValue = String.valueOf(random.nextInt());
        entity.setShortUrl(randomValue);
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
        accessCount(newUrl);

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
