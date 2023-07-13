package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AddUserWatchListResponse extends BaseResponse {
    ArrayList<AddUserWatchListEntity> data;

    public AddUserWatchListResponse(int code, String message, ArrayList<AddUserWatchListEntity> data) {
        super(code, message);

        this.data = data;
    }
}
