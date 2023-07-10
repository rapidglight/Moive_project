package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserMovieCommentResponse extends BaseResponse {
    ArrayList<UserMovieCommentEntity> data;

    public UserMovieCommentResponse(int code, String message, ArrayList<UserMovieCommentEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }
}
