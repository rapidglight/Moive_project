package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MovieimgResponse extends BaseResponse {
    ArrayList<MovieimgEntity> data;

    public MovieimgResponse(int code, String message, ArrayList<MovieimgEntity> data) {
        super(code, message);

        this.data = data;
    }

}
