package com.kapiladithyaa;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UrlStore {

    private final Map<String, String> codeToUrl = new ConcurrentHashMap<>();
    private final Map<String, String> urlToCode = new ConcurrentHashMap<>();

    private long counter = 1;

    public synchronized String shorten(String longUrl) {

        if (!isValidUrl(longUrl)) {
            throw new IllegalArgumentException("Invalid URL: " + longUrl);
        }

        String existing = urlToCode.get(longUrl);

        if (existing != null) {
            return existing;
        }

        String code = Base62.encode(counter++);

        codeToUrl.put(code, longUrl);
        urlToCode.put(longUrl, code);

        return code;
    }

    public String resolve(String code) {

        String url = codeToUrl.get(code);

        if (url == null) {
            throw new CodeNotFoundException(code);
        }

        return url;
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