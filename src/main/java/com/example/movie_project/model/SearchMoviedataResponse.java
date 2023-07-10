package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class SearchMoviedataResponse extends BaseResponse {

    ArrayList<SearchMoviedataEntity> data;

    public SearchMoviedataResponse(int code, String message, ArrayList<SearchMoviedataEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }

}
