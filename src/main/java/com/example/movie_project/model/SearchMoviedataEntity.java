package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class SearchMoviedataEntity {
    int movieId;
    String movieName;
    String imageUrl;
    int year;
    String age;
    int movieLong;
    int movieCategoryRomance;
    int movieCategoryComedy;
    int movieCategoryDrama;
    int movieCategoryAction;
    String movieDescription;
    String movieDirector;
    String movieCast;
}
