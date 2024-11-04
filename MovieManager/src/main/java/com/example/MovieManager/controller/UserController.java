package com.example.MovieManager.controller;

import com.example.MovieManager.DTO.UserDTO;
import com.example.MovieManager.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MovieManager.entity.user.User;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UUID> registerUser(@RequestBody User user) {
        UUID userId = userServices.registerUser(user).getUserId();
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDTO> getUser(@RequestParam(value = "uuid") UUID userId) {
        Optional<UserDTO> userDTO = userServices.getUser(userId
        );

        return userDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
