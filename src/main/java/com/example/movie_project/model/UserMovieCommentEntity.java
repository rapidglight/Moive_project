package com.example.movie_project.model;

import lombok.Data;

@Data
public class UserMovieCommentEntity {
    int id;
    String userAccount;
    String userRatingMovieName;
    int userRatingMovieStars;
    String userRatingMovieComments;
}
