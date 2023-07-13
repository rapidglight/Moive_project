package com.example.movie_project.model;

import lombok.Data;

@Data
public class WebsiteRatingEntity {
    int MovieId;
    String imageUrl;
    String userRatingMovieName;
    int userRatingMovieStars;
    Double userRatingMovieStar;
}
