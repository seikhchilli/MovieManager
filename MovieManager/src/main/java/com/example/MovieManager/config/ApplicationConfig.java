package com.example.MovieManager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Value("${external.api.tmdb.url}")
    private String tmdbBaseUrl;

    @Value("${external.api.tmdb.access_token}")
    private String access_token;

    @Bean
    public WebClient tmdbWebClient() {
        return WebClient.builder().baseUrl(tmdbBaseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, access_token).build();
    }
}
      