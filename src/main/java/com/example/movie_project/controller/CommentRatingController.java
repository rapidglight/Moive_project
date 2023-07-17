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

import com.example.movie_project.model.CommentRatingEntity;
import com.example.movie_project.model.CommentRatingResponse;
import com.example.movie_project.model.WebsiteRatingResponse;

@RestController

public class CommentRatingController {
    @RequestMapping(value="/commentrating", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentRatingResponse movies() {
        return getCommentRating();
    }

    private CommentRatingResponse getCommentRating() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                "SELECT count(user_rating_movie_name), image_url, user_rating_movie_name, user_rating_movie_comments, movie_id"
                +" FROM user_rating_list JOIN javaconnect.movie ON user_rating_movie_name = movie_name"
                +" WHERE user_rating_movie_comments is not null"
                +" GROUP BY movie.image_url, user_rating_movie_name"
                +" ORDER by count(user_rating_movie_name) desc limit 5"
            );

            ArrayList<CommentRatingEntity> movies = new ArrayList<>();
            while (rs.next()) {
                CommentRatingEntity commentRatingEntity = new CommentRatingEntity();
                commentRatingEntity.setUserRatingMovieName(rs.getString("user_rating_movie_name"));
                commentRatingEntity.setImgUrl(rs.getString("image_url"));
                commentRatingEntity.setUserRatingComment(rs.getString("user_rating_movie_comments"));
                commentRatingEntity.setCountUserRating(rs.getInt("count(user_rating_movie_name)"));
                commentRatingEntity.setMovieId(rs.getInt("movie_id"));
                
                movies.add(commentRatingEntity);
                

            }
            return new CommentRatingResponse(0, "成功", movies);
            }
            catch (SQLException e) {
            return new CommentRatingResponse(e.getErrorCode(), e.getMessage(), null);
            }
            catch (ClassNotFoundException e) {
            return new CommentRatingResponse(1, "無法註冊驅動程式", null);
            }
        }
    }