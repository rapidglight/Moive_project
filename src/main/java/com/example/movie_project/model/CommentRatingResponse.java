package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CommentRatingResponse extends BaseResponse {
    ArrayList<CommentRatingEntity> data;
    
    public CommentRatingResponse(int code, String message, ArrayList<CommentRatingEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }

    
}
