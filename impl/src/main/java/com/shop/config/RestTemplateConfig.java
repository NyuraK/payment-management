package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    public static final String LOCALHOST_URL = "http://localhost:%s/%s";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
