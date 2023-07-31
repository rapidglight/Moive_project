package com.example.movie_project.controller;

import com.example.movie_project.model.BaseResponse;
import com.example.movie_project.model.MovieratingEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MovieratingcomController {

    @RequestMapping(value = "/movieratingcom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    public BaseResponse updateMovierating(@RequestBody MovieratingEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=0000");
            stmt = conn.prepareStatement(
                    "INSERT INTO user_rating_list (user_account, user_rating_movie_comments, user_rating_movie_name) " +
                            "VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "user_rating_movie_comments = VALUES(user_rating_movie_comments)");

            stmt.setString(1, data.getUserAccount());
            stmt.setString(2, data.getUserRatingMovieComments());
            stmt.setString(3, data.getUserRatingMovieName());

            int rowsAffected = stmt.executeUpdate();

            return new BaseResponse(0, "更新成功");
        } catch (SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        } catch (ClassNotFoundException e) {
            return new BaseResponse(1, "無法註冊驅動程式");
        }
    }

}
