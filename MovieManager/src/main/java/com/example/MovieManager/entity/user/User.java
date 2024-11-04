package com.example.MovieManager.entity.user;

import com.example.MovieManager.common.Gender;
import com.example.MovieManager.entity.movie.TitleBasic;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false, unique = true)
    private UUID userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(10)
    @Max(60)
    private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "watched_list",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "tconst", referencedColumnName = "tconst"))
    private Set<TitleBasic> watchedList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "wish_list",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "tconst", referencedColumnName = "tconst"))
    private Set<TitleBasic> wishList;

}