package com.kapiladithyaa;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlStore {

    private final UrlMappingRepository repository;

    public UrlStore(UrlMappingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String shorten(String longUrl) {

        if (!isValidUrl(longUrl)) {
            throw new IllegalArgumentException("Invalid URL: " + longUrl);
        }

        return repository.findByLongUrl(longUrl)
                .map(UrlMapping::getCode)
                .orElseGet(() -> create(longUrl));
    }

    private String create(String longUrl) {

        // First save: generates the database ID
        UrlMapping saved = repository.saveAndFlush(
                new UrlMapping(longUrl)
        );

        // Generate the short code from the generated ID
        String code = Base62.encode(saved.getId());

        // Set the code
        saved.setCode(code);

        // Second save: explicitly persist the generated code
        repository.saveAndFlush(saved);

        return code;
    }

    @Transactional(readOnly = true)
    public String resolve(String code) {

        return repository.findByCode(code)
                .map(UrlMapping::getLongUrl)
                .orElseThrow(() -> new CodeNotFoundException(code));
    }

    private boolean isValidUrl(String url) {

        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();

            return uri.getHost() != null
                    && ("http".equals(scheme) || "https".equals(scheme));

        } catch (Exception e) {
            return false;
        }
    }
}