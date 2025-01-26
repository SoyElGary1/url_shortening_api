package com.url_shortening.controller;

import com.url_shortening.dto.UrlRequestDto;
import com.url_shortening.dto.UrlResponseDto;
import com.url_shortening.dto.UrlStatsResponseDto;
import com.url_shortening.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/shorten")
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping()
    public ResponseEntity<UrlResponseDto> shorten(@RequestBody UrlRequestDto url) {
        try {
            UrlResponseDto url1 = urlService.saveUrl(url);
            return ResponseEntity.created(URI.create("/api/v1/shorten/" + url1.id())).body(url1);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<UrlResponseDto> getUrl(@PathVariable String shortUrl) {
        try{
            UrlResponseDto url = urlService.getUrl(shortUrl);
            return ResponseEntity.ok(url);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{shortUrl}/stats")
    public ResponseEntity<UrlStatsResponseDto> getUrlStats(@PathVariable String shortUrl) {
        try{
            UrlStatsResponseDto url = urlService.getUrlStats(shortUrl);
            return ResponseEntity.ok(url);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{shortUrl}")
    public ResponseEntity<UrlResponseDto> updateUrl(@PathVariable String shortUrl, @RequestBody UrlRequestDto url) {
        try {
            UrlResponseDto urlUpdated = urlService.updateUrl(shortUrl, url);
            return ResponseEntity.ok(urlUpdated);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{shortUrl}")
    public ResponseEntity deleteUrl(@PathVariable String shortUrl) {
        try {
            urlService.deleteUrl(shortUrl);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
