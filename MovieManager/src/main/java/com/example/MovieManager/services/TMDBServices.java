package com.example.MovieManager.services;
import com.example.MovieManager.DTO.movie.MovieImagesResponse;
import com.example.MovieManager.DTO.movie.Poster;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class TMDBServices {
    @Autowired
    private TMDBApiServices tmdbApiServices;


    public String getHighestVotedPosterUrl(String tconst) {
        MovieImagesResponse movieImagesResponse = tmdbApiServices.fetchMovieImages(tconst);

        if (movieImagesResponse == null || movieImagesResponse.posters().isEmpty()) {
            return "NA";
        }

        return findHighestVotedPosterUrl(movieImagesResponse);

    }

    private String findHighestVotedPosterUrl(MovieImagesResponse movieImagesResponse) {
        return movieImagesResponse.posters().stream()
                .max(Comparator.comparingInt(Poster::vote_count))
                .map(Poster::file_path)
                .map(filePath -> "https://image.tmdb.org/t/p/original" + filePath)
                .orElse("NA");
    }

}
