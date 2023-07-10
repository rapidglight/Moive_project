package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MovieStringArrayResponse extends BaseResponse {
    ArrayList<String> data;

    public MovieStringArrayResponse(int code, String message, ArrayList<String> data) {
        super(code, message);

        this.data = data;
    }
}
