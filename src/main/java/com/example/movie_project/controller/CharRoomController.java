package com.example.movie_project.controller;

import com.example.movie_project.model.ChatClientModel;
import com.example.movie_project.model.ServerResponseModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;





@Controller
public class CharRoomController {
@MessageMapping("/messageControl")
@SendTo("/topic/getResponse")
@CrossOrigin(value = "*")
public ServerResponseModel said(ChatClientModel request) throws InterruptedException {
    Thread.sleep(3000);
    String responseMessage = ":" + request.getMessage();
    return new ServerResponseModel(responseMessage);
}

}

