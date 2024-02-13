package com.flixsa.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */

@Configuration
@ConfigurationProperties(prefix = "secrets")
@Data
public class AppConfig {
    private String randomkey;
}