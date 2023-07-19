package com.example.movie_project;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

public class CustomHeartbeatHandler extends WebSocketHandlerDecorator {

    private final long heartbeatTime;

    public CustomHeartbeatHandler(WebSocketHandler delegate, long heartbeatTime) {
        super(delegate);
        this.heartbeatTime = heartbeatTime;
    }

    // 實現心跳邏輯，例如在指定間隔發送心跳消息等
    // ...

}

