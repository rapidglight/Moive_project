package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class WebsiteRatingResponse extends BaseResponse {
    ArrayList<WebsiteRatingEntity> data;

    public WebsiteRatingResponse(int code, String message, ArrayList<WebsiteRatingEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }
}
