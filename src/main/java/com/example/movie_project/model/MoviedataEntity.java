//電影頁面資料entity

package com.example.movie_project.model;

import lombok.Data;

@Data
public class MoviedataEntity {
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
    String movieTrailer;
}
