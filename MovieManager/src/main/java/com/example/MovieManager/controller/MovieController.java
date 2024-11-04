package com.example.MovieManager.controller;

import com.example.MovieManager.DTO.UserDTO;
import com.example.MovieManager.DTO.movie.TitleBasicDTO;
import com.example.MovieManager.services.MovieServices;
import com.example.MovieManager.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieServices movieServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("/getDetails")
    public ResponseEntity<TitleBasicDTO> getMovie(@RequestParam(value = "tconst") String tconst) {
        Optional<TitleBasicDTO> optionalTitleBasicDTO = movieServices.getTitleBasic(tconst);

        return optionalTitleBasicDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TitleBasicDTO>> searchMovie(@RequestParam String originalTitle) {
        return new ResponseEntity<>(movieServices.searchTitleBasic(originalTitle), HttpStatus.OK);
    }

    @GetMapping("/watched")
    public ResponseEntity<Set<TitleBasicDTO>> getWatchedList(@RequestHeader(value = "x-user-id") UUID userId) {
        Optional<UserDTO> optionalUserDTO = userServices.getUser(userId);

        return optionalUserDTO.map(userDTO -> new ResponseEntity<>(userDTO.watchedList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/watched")
    public ResponseEntity<Set<TitleBasicDTO>> updateWatchedList(@RequestHeader(value = "x-user-id") UUID userId, @RequestParam(value = "tconst") String tconst) {
        Optional<UserDTO> optionalUserDTO = userServices.updateWatchedList(userId, tconst);

        return optionalUserDTO.map(userDTO -> new ResponseEntity<>(userDTO.watchedList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
