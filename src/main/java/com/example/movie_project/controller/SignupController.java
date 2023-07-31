package com.example.movie_project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_project.model.BaseResponse;
import com.example.movie_project.model.SingupEntity;
import com.example.movie_project.model.SingupResponse;

@RestController
public class SignupController {

    @RequestMapping(value = "/singup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse SingupAccount(@RequestBody SingupEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        // 註冊mysql資料庫驅動程式
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?" +
                    "user=root&password=0000");
            stmt = conn.prepareStatement(
                    "INSERT INTO userdata  (user_account, user_password, user_mail) VALUES (?, ?, ?)");

            stmt.setString(1, data.getUserAccount());
            stmt.setString(2, data.getUserPassword());
            stmt.setString(3, data.getUserMail());
            // 判斷帳號是否存在
            int rowsAffected = stmt.executeUpdate();

            return new BaseResponse(0, "更新成功");
        } catch (SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        } catch (ClassNotFoundException e) {
            return new BaseResponse(1, "無法註冊驅動程式");
        }
    }
}
