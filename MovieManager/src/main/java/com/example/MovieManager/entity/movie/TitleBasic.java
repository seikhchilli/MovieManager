package com.example.MovieManager.entity.movie;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="title_basic")
public class TitleBasic {
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    private String tconst;


    private String titleType;


    private String primaryTitle;


    private String originalTitle;


    private Boolean isAdult;


    private Short startYear;


    private Short endYear;


    private Double runtimeMinutes;


    private String genres;


    private String imgUrl;


    private Double averageRating;

}
