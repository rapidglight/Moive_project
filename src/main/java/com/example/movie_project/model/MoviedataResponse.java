package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MoviedataResponse extends BaseResponse {

    ArrayList<MoviedataEntity> data;

    public MoviedataResponse(int code, String message, ArrayList<MoviedataEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }

}
