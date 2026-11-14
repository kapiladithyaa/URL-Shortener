package com.kapiladithyaa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UrlStoreTest {

    @Autowired
    private UrlStore store;

    @Test
    void shortenReturnsCode() {
        String code = store.shorten("https://www.google.com");

        assertNotNull(code);
        assertEquals(
                "https://www.google.com",
                store.resolve(code)
        );
    }

    @Test
    void sameUrlGivesSameCode() {
        String first = store.shorten("https://www.google.com");
        String second = store.shorten("https://www.google.com");

        assertEquals(first, second);
    }

    @Test
    void differentUrlsGiveDifferentCodes() {
        String a = store.shorten("https://www.google.com");
        String b = store.shorten("https://github.com");

        assertNotEquals(a, b);
    }

    @Test
    void resolveReturnsOriginalUrl() {
        String code = store.shorten("https://github.com");

        assertEquals(
                "https://github.com",
                store.resolve(code)
        );
    }

    @Test
    void resolveUnknownCodeThrows() {
        assertThrows(
                CodeNotFoundException.class,
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