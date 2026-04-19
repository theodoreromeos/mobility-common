package com.theodore.infrastructure.common.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "mobility.oauth2")
public record OAuth2ResourceProperties(Map<String, String> resources) {

    public OAuth2ResourceProperties {
        resources = resources != null ? Map.copyOf(resources) : Map.of();
    }

}