package com.example.movie_project.model;

import lombok.Data;

@Data

// 父類別建構式有參數，所以子類別一定要有
public class LoginResponse extends BaseResponse {
    public LoginResponse(int code, String message) {
        super(code, message);
    }

}
