package com.example.movie_project.model;

import java.util.ArrayList;
import lombok.Data;

//自動產生getter、setter
@Data

public class BaseResponse {
    protected int code;
    protected String message;

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
