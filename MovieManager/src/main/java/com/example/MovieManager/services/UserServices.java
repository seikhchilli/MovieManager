package com.example.MovieManager.services;

import com.example.MovieManager.DTO.UserDTO;
import com.example.MovieManager.DTO.movie.TitleBasicDTO;
import com.example.MovieManager.entity.movie.TitleBasic;
import com.example.MovieManager.mappers.movie.TitleBasicMapper;
import com.example.MovieManager.mappers.user.UserMapper;
import com.example.MovieManager.repository.movie.TitleBasicRepository;
import com.example.MovieManager.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.MovieManager.entity.user.User;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TitleBasicRepository titleBasicRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<UserDTO> getUser(UUID userId) {
        return userRepository.findById(userId).map(UserMapper::toDTO);
    }

    public Optional<UserDTO> updateWatchedList(UUID userId, String tconst) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            return Optional.empty();
        }

        User user = optionalUser.get();
        System.out.println("searching for: " + tconst);
        Optional<TitleBasic> optionalTitleBasic = titleBasicRepository.findById(tconst);

        if (optionalTitleBasic.isEmpty()) {
            System.out.println("Returning since optionalTitleBasic empty");
            return Optional.empty();
        }

        TitleBasic titleBasic = optionalTitleBasic.get();

        user.getWatchedList().add(titleBasic);

        User updatedUser = userRepository.save(user);

        return Optional.of(UserMapper.toDTO(updatedUser));
    }
}
