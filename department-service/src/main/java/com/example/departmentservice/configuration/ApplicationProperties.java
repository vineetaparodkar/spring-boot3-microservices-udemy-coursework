package com.example.departmentservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @implNote : One place to access all application properties
 */
@Configuration
public class ApplicationProperties {

    @Value("${web.config.allowed-origin-urls}")
    private List<String> allowedOriginURLs;

    @Value("${web.config.max-age}")
    private Long maxAge;


    public List<String> getAllowedOriginURLs() {
        return allowedOriginURLs;
    }

    public Long getMaxAge() {
        return maxAge;
    }

}
