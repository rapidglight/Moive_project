package com.example.movie_project.controller;

import com.example.movie_project.model.UserRatingListEntity;
import com.example.movie_project.model.UserRatingListResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserRatingListController {
    @RequestMapping(value = "/userratinglist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    public UserRatingListResponse movies(String username) {
        return getuserratingList(username);
    }

    private UserRatingListResponse getuserratingList(String username) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=0000");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select user_account ,user_rating_movie_name ,user_rating_movie_stars from user_rating_list where user_account='"
                            + username + "'");
            ArrayList<UserRatingListEntity> movies = new ArrayList<>();
            while (rs.next()) {
                UserRatingListEntity userRatingListEntity = new UserRatingListEntity();
                userRatingListEntity.setUserRatingMovieName(rs.getString("user_rating_movie_name"));
                userRatingListEntity.setUserRatingMovieStars(rs.getInt("user_rating_movie_stars"));
                movies.add(userRatingListEntity);
            }
            return new UserRatingListResponse(0, "成功", movies);
        } catch (SQLException e) {
            return new UserRatingListResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new UserRatingListResponse(1, "無法註冊驅動程式", null);
        }
    }
}
