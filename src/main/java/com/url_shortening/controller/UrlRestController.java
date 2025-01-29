package com.url_shortening.controller;

import com.url_shortening.dto.UrlRequestDto;
import com.url_shortening.dto.UrlResponseDto;
import com.url_shortening.dto.UrlStatsResponseDto;
import com.url_shortening.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/shorten/")
public class UrlRestController {

    private UrlService urlService;

    public UrlRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponseDto> saveUrl(@RequestBody @Valid UrlRequestDto url) {
        try {
            String baseUrl = "http://localhost:8080/redirect/";

            UrlResponseDto savedUrl = urlService.saveUrl(url);
            if (savedUrl == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            return ResponseEntity.created(URI.create(baseUrl + savedUrl.shortUrl())).body(savedUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("{shortUrl}")
    public ResponseEntity<UrlResponseDto> getUrl(@PathVariable String shortUrl) {
        try {
            UrlResponseDto url = urlService.getUrl(shortUrl);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{shortUrl}/stats")
    public ResponseEntity<UrlStatsResponseDto> getUrlStats(@PathVariable String shortUrl) {
        try {
            UrlStatsResponseDto url = urlService.getUrlStats(shortUrl);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{shortUrl}")
    public ResponseEntity<UrlResponseDto> updateUrl(@PathVariable String shortUrl, @RequestBody @Valid UrlRequestDto url) {

        try {
            UrlResponseDto urlUpdated = urlService.updateUrl(shortUrl, url);
            return ResponseEntity.ok(urlUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{shortUrl}")
    public ResponseEntity deleteUrl(@PathVariable String shortUrl) {
        try {
            urlService.deleteUrl(shortUrl);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
