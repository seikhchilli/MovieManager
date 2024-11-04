package com.example.MovieManager.mappers.movie;

import com.example.MovieManager.DTO.movie.TitleBasicDTO;
import com.example.MovieManager.entity.movie.TitleBasic;

public class TitleBasicMapper {

    public static TitleBasicDTO toDTO(TitleBasic titleBasic) {
        if (titleBasic == null) {
            return null;
        }

        return new TitleBasicDTO(
                titleBasic.getTconst(),
                titleBasic.getTitleType(),
                titleBasic.getPrimaryTitle(),
                titleBasic.getOriginalTitle(),
                titleBasic.getIsAdult(),
                titleBasic.getStartYear(),
                titleBasic.getEndYear(),
                titleBasic.getRuntimeMinutes(),
                titleBasic.getGenres(),
                titleBasic.getAverageRating(),
                titleBasic.getImgUrl()
        );
    }
}
