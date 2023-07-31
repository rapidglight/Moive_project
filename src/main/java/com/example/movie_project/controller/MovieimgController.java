//這個API只傳電影名稱 ID 跟URL(6筆)

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

import com.example.movie_project.model.MovieimgEntity;
import com.example.movie_project.model.MovieimgResponse;

@RestController
public class MovieimgController {

    @RequestMapping(value = "/movieimg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieimgResponse movies() {
        return getMovieimgList();

    }

    private MovieimgResponse getMovieimgList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery("select movie_id,movie_name,image_url from movie order by rand() limit 9");

            ArrayList<MovieimgEntity> movies = new ArrayList<>();

            while (rs.next()) {
                MovieimgEntity movieimgEntity = new MovieimgEntity();
                movieimgEntity.setId(rs.getInt("movie_id"));
                movieimgEntity.setMovie_name(rs.getString("movie_name"));
                movieimgEntity.setImage_url(rs.getString("image_url"));

                movies.add(movieimgEntity);

            }

            return new MovieimgResponse(0, "成功", movies);

        } catch (SQLException e) {
            return new MovieimgResponse(e.getErrorCode(), e.getMessage(), null);

        } catch (ClassNotFoundException e) {
            return new MovieimgResponse(1, "無法註冊驅動程式", null);

        }
    }
}
