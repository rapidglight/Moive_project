package com.example.movie_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MovieProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieProjectApplication.class, args);
	}

}
