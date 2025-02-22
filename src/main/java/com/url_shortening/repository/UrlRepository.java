package com.url_shortening.repository;

import com.url_shortening.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url getReferenceByShortUrl(String shortUrl);

    boolean existsByShortUrl(String shortUrlId);
}
