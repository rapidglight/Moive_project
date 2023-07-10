
package com.example.movie_project.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MovieratingResponse extends BaseResponse {

    ArrayList<MovieratingEntity> data;

    public MovieratingResponse(int code, String message, ArrayList<MovieratingEntity> data) {
        super(code, message);
        // TODO Auto-generated constructor stub
        this.data = data;
    }

}
