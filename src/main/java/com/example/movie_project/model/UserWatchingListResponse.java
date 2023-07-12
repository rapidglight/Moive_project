package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserWatchingListResponse extends BaseResponse {
    ArrayList<UserWatchingListEntity> data;

    public UserWatchingListResponse(int code, String message, ArrayList<UserWatchingListEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }
}
