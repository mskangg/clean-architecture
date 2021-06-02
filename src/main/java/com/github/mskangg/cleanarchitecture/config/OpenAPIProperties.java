package com.github.mskangg.cleanarchitecture.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "open.api")
public class OpenAPIProperties {
    private String url;
    private String serviceKey;
    private String returnType;
}