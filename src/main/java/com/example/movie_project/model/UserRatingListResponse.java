package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserRatingListResponse extends BaseResponse {
    ArrayList<UserRatingListEntity> data;

    public UserRatingListResponse(int code, String message, ArrayList<UserRatingListEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }
}
