package com.url_shortening.controller;

import com.url_shortening.dto.UrlResponseDto;
import com.url_shortening.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/redirect/")
public class UrlRedirectRestController {

    private UrlService urlService;
    public UrlRedirectRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("{shortUrl}")
    public RedirectView redirect(@PathVariable("shortUrl") String shortUrl) {
        if (shortUrl == null || shortUrl.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid short URL");
        }
        try {
            UrlResponseDto url = urlService.getUrl(shortUrl);
            if (url == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found: " + shortUrl);
            }
            return new RedirectView(url.url());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during redirection", e);
        }
    }
}
