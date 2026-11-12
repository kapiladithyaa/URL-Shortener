package com.kapiladithyaa;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class UrlStore {

    private final Map<String, String> codeToUrl = new HashMap<>();
    private final Map<String, String> urlToCode = new HashMap<>();

    private long counter = 1;

    public String shorten(String longUrl) {

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
            throw new IllegalArgumentException("Code not found: " + code);
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