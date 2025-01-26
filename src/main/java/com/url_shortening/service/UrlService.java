package com.url_shortening.service;

import com.url_shortening.model.Url;
import com.url_shortening.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Url saveUrl(Url url) {
        Random random = new Random();
        random.longs(5L);
        String randomValue = String.valueOf(random.nextInt());
        url.setShortUrl(randomValue);
        url.setAccessCount(0);
        return repository.save(url);
    }

    @Transactional
    public Url findUrlByShortUrl(String shortUrl) {
        Url newUrl = repository.getReferenceByShortUrl(shortUrl);
        accessCount(newUrl);

        return newUrl;
    }

    @Transactional
    public Url updateUrl(String shortUrl, Url newUrl) {
        Url url = repository.getReferenceByShortUrl(shortUrl);
        if (newUrl != null) {
            url.setUrl(newUrl.getUrl());
        }
        return url;
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
