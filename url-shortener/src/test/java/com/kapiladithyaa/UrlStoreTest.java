package com.kapiladithyaa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UrlStoreTest {

    private UrlStore store;

    @BeforeEach
    void setUp() {
        store = new UrlStore();
    }

    @Test
    void shortenReturnsCode() {
        String code = store.shorten("https://www.google.com");

        assertEquals("1", code);
    }

    @Test
    void sameUrlGivesSameCode() {
        String first = store.shorten("https://www.google.com");
        String second = store.shorten("https://www.google.com");

        assertEquals(first, second);
    }

    @Test
    void differentUrlsGiveDifferentCodes() {
        String google = store.shorten("https://www.google.com");
        String github = store.shorten("https://github.com");

        assertNotEquals(google, github);
    }

    @Test
    void resolveReturnsOriginalUrl() {
        String code = store.shorten("https://github.com");

        assertEquals("https://github.com", store.resolve(code));
    }

    @Test
    void resolveUnknownCodeThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> store.resolve("zzz")
        );
    }

    @Test
    void invalidUrlThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> store.shorten("hello")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> store.shorten("ftp://example.com")
        );
    }
}