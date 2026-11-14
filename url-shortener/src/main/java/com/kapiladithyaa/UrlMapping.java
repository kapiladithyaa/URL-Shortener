package com.kapiladithyaa;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(
    name = "url_mapping",
    indexes = @Index(
        name = "idx_long_url",
        columnList = "long_url"
    )
)
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "long_url", nullable = false, length = 2048)
    private String longUrl;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    protected UrlMapping() {}

    public UrlMapping(String longUrl) {
        this.longUrl = longUrl;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}