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

import com.example.movie_project.model.WebsiteRatingEntity;
import com.example.movie_project.model.WebsiteRatingResponse;

@RestController

public class AvgmovieController {
    @RequestMapping(value = "/websiteratings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebsiteRatingResponse movies(String moviename) {
        return getWebsiteRating(moviename);

    }

    private WebsiteRatingResponse getWebsiteRating(String moviename) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");
            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "SELECT movie.movie_id, movie.image_url, movie.movie_name, user_rating_movie_name, AVG(user_rating_movie_stars) "
                            +
                            "FROM user_rating_list " +
                            "JOIN javaconnect.movie " +
                            "ON user_rating_movie_name = movie_name " +
                            "WHERE user_rating_movie_name = '" + moviename + "' " +
                            "GROUP BY movie.movie_id, movie.image_url, movie.movie_name, user_rating_movie_name " +
                            "ORDER BY AVG(user_rating_movie_stars)");

            ArrayList<WebsiteRatingEntity> movies = new ArrayList<>();

            while (rs.next()) {
                WebsiteRatingEntity websiteRatingEntity = new WebsiteRatingEntity();
                websiteRatingEntity.setUserRatingMovieName(rs.getString("user_rating_movie_name"));
                websiteRatingEntity.setUserRatingMovieStar(rs.getDouble("AVG(user_rating_movie_stars)"));

                movies.add(websiteRatingEntity);
            }

            return new WebsiteRatingResponse(0, "成功", movies);
        } catch (SQLException e) {
            return new WebsiteRatingResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new WebsiteRatingResponse(1, "無法註冊驅動程式", null);
        }

    }

}
