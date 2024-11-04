package com.example.MovieManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MovieManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieManagerApplication.class, args);
	}

}
