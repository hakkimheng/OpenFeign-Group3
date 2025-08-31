package com.jpa.openfeign.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient studentWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://67d2fa458bca322cc268b50c.mockapi.io/student")
                .build();
    }
}
