package com.url_shortening.controller;

import com.url_shortening.model.Url;
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
    public ResponseEntity<Url> shorten(@RequestBody Url url) {
        try {
            Url url1 = urlService.saveUrl(url);
            return ResponseEntity.created(URI.create("/api/v1/shorten/" + url1.getId())).body(url1);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Url> getUrlByShorten(@PathVariable String shortUrl) {
        try{
            Url url = urlService.findUrlByShortUrl(shortUrl);
            return ResponseEntity.ok(url);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{shortUrl}")
    public ResponseEntity<Url> updateUrl(@PathVariable String shortUrl, @RequestBody Url url) {
        try {
            Url urlUpdated = urlService.updateUrl(shortUrl, url);
            return ResponseEntity.ok(urlUpdated);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{shortUrl}")
    public ResponseEntity<Url> deleteUrl(@PathVariable String shortUrl) {
        try {
            urlService.deleteUrl(shortUrl);
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
