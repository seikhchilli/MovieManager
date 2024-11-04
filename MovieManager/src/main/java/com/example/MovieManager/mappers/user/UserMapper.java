package com.example.MovieManager.mappers.user;

import com.example.MovieManager.DTO.UserDTO;
import com.example.MovieManager.DTO.movie.TitleBasicDTO;
import com.example.MovieManager.entity.user.User;
import com.example.MovieManager.mappers.movie.TitleBasicMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if(user == null) return null;

        Set<TitleBasicDTO> watchedListDTO = user.getWatchedList()
                .stream()
                .map(TitleBasicMapper::toDTO)
                .collect(Collectors.toSet());

        Set<TitleBasicDTO> wishListDTO = user.getWishList()
                .stream()
                .map(TitleBasicMapper::toDTO)
                .collect(Collectors.toSet());

        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getGender(),
                watchedListDTO,
                wishListDTO
        );
    }
}