package com.example.movie_project.model;

public class ServerResponseModel {
    private String responseMessage;

    public ServerResponseModel() {
    }

    public ServerResponseModel(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
