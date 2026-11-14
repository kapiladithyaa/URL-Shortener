package com.kapiladithyaa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository
        extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByCode(String code);

    Optional<UrlMapping> findByLongUrl(String longUrl);
}