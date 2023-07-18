package com.example.movie_project.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.movie_project.model.ChatClientModel;
import com.example.movie_project.model.ServerResponseModel;

@Controller
public class CharRoomController {
    @MessageMapping("/messageControl")
    @SendTo("topic/getResponse")
    public ServerResponseModel said(ChatClientModel responseMessage) throws InterruptedException {
        Thread.sleep(3000);
        return new ServerResponseModel();
    }
}
