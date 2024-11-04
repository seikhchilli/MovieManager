package com.example.MovieManager.services;

import com.example.MovieManager.DTO.movie.TitleBasicDTO;
import com.example.MovieManager.entity.movie.TitleBasic;
import com.example.MovieManager.mappers.movie.TitleBasicMapper;
import com.example.MovieManager.repository.movie.TitleBasicRepository;
import com.example.MovieManager.specification.movie.TitleBasicSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Service
public class MovieServices {
    @Autowired
    private TitleBasicRepository titleBasicRepository;

    @Autowired
    private TMDBServices tmdbServices;

    public Optional<TitleBasicDTO> getTitleBasic(String tconst) {
        return titleBasicRepository.findById(tconst).map(this::fetchAndUpdateImgUrl).map(TitleBasicMapper::toDTO);
    }

    public List<TitleBasicDTO> searchTitleBasic(String originalTitle) {
        Specification<TitleBasic> spec = Specification.where(TitleBasicSpecification.hasOriginalTitle(originalTitle));
        return titleBasicRepository.findAll(spec).stream().map(this::fetchAndUpdateImgUrl).map(TitleBasicMapper::toDTO).toList();
    }

    private TitleBasic fetchAndUpdateImgUrl(TitleBasic titleBasic) {
        if(titleBasic == null) {
            return null;
        }

        if(titleBasic.getImgUrl() == null){
            String imgUrl = tmdbServices.getHighestVotedPosterUrl(titleBasic.getTconst());

            if(imgUrl != null) {
                titleBasic.setImgUrl(imgUrl);

                CompletableFuture.runAsync(() -> titleBasicRepository.save(titleBasic));
            }
        }
        return titleBasic;
    }

}
