package com.example.MovieManager.specification.movie;

import com.example.MovieManager.entity.movie.TitleBasic;
import org.springframework.data.jpa.domain.Specification;

public class TitleBasicSpecification {
    public static Specification<TitleBasic> hasOriginalTitle(String originalTitle) {
        return ((root, query, criteriaBuilder) -> originalTitle != null ? criteriaBuilder.like(root.get("primaryTitle"), originalTitle + "%") : null);
    }
}
