package com.example.MovieManager.DTO.movie;

public record TitleBasicDTO(
        String tconst,
        String titleType,
        String primaryTitle,
        String originalTitle,
        Boolean isAdult,
        Short startYear,
        Short endYear,
        Double runtimeMinutes,
        String genres,
        Double averageRating,
        String imgUrl
) {}
