package com.example.movie_project.model;

import lombok.Data;

@Data
public class CommentRatingEntity {
    String userRatingMovieName;
    String userRatingComment;
    String imgUrl;
    int countUserRating;
    int movieId;
}