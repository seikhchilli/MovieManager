package com.example.MovieManager.DTO;
import com.example.MovieManager.DTO.movie.TitleBasicDTO;
import com.example.MovieManager.common.Gender;

import java.util.Set;
import java.util.UUID;

public record UserDTO(UUID userId, String firstName, String lastName, Integer age, Gender gender, Set<TitleBasicDTO> watchedList, Set<TitleBasicDTO> wishList) {
}
