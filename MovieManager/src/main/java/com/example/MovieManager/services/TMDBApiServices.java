package com.example.MovieManager.services;

import com.example.MovieManager.DTO.movie.MovieImagesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Slf4j
@Service
public class TMDBApiServices {
    private final WebClient tmdbWebClient;

    @Autowired
    public TMDBApiServices(WebClient tmdbWebClient) {
        this.tmdbWebClient = tmdbWebClient;

    }

    public MovieImagesResponse fetchMovieImages(String tconst) {
        try {
            return tmdbWebClient.get()
                    .uri("/movie/{movieId}/images", tconst)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(MovieImagesResponse.class)
                    .block();
        }
        catch (WebClientResponseException.NotFound exception) {
            log.error("e: ", exception);
            return null;
        }
    }
}
