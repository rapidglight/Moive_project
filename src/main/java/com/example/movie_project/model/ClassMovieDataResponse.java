package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ClassMovieDataResponse extends BaseResponse {
    ArrayList<ClassMovieDataEntity> data;

    public ClassMovieDataResponse(int code, String message, ArrayList<ClassMovieDataEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }
}
