package com.example.movie_project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_project.model.UserMovieCommentEntity;
import com.example.movie_project.model.UserMovieCommentResponse;

@RestController
public class UserMovieCommentController {
    @RequestMapping(value = "/usercomment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMovieCommentResponse movies(String moviename) {
        return getUserCommentList(moviename);

    }

    private UserMovieCommentResponse getUserCommentList(String moviename) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "select * from user_rating_list where user_rating_movie_name='" + moviename + "'");

            ArrayList<UserMovieCommentEntity> movies = new ArrayList<>();

            int id;
            String userAccount;
            String userRatingMovieName;
            int userRatingMovieStars;
            String userRatingMovieComments;

            while (rs.next()) {
                UserMovieCommentEntity UserMovieCommentEntity = new UserMovieCommentEntity();
                UserMovieCommentEntity.setId(rs.getInt("rating_list_id"));
                UserMovieCommentEntity.setUserAccount(rs.getString("user_account"));
                UserMovieCommentEntity.setUserRatingMovieComments(rs.getString("user_rating_movie_comments"));
                UserMovieCommentEntity.setUserRatingMovieName(rs.getString("user_rating_movie_name"));
                UserMovieCommentEntity.setUserRatingMovieStars(rs.getInt("user_rating_movie_stars"));

                movies.add(UserMovieCommentEntity);

            }
            return new UserMovieCommentResponse(0, "成功", movies);

        } catch (SQLException e) {
            return new UserMovieCommentResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new UserMovieCommentResponse(1, "無法註冊驅動程式", null);
        }

    }
}
