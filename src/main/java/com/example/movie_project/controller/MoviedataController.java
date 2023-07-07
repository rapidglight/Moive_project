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

import com.example.movie_project.model.MoviedataEntity;
import com.example.movie_project.model.MoviedataResponse;

@RestController
public class MoviedataController {

    @RequestMapping(value = "/moviedata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public MoviedataResponse movies() {
        return getMoviedataList();

    }

    /**
     * @return
     */
    private MoviedataResponse getMoviedataList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery(
                    "select * from movie");

            ArrayList<MoviedataEntity> movies = new ArrayList<>();

            while (rs.next()) {
                MoviedataEntity MoviedataEntity = new MoviedataEntity();
                MoviedataEntity.setMovieId(rs.getInt("movie_id"));
                MoviedataEntity.setMovieName(rs.getString("movie_name"));
                MoviedataEntity.setImageUrl(rs.getString("image_url"));
                MoviedataEntity.setYear(rs.getInt("year"));
                MoviedataEntity.setAge(rs.getString("age"));
                MoviedataEntity.setMovieLong(rs.getInt("movie_long"));
                MoviedataEntity.setMovieCategoryRomance(rs.getInt("movie_category_Romance"));
                MoviedataEntity.setMovieCategoryComedy(rs.getInt("movie_category_Comedy"));
                MoviedataEntity.setMovieCategoryDrama(rs.getInt("movie_category_Drama"));
                MoviedataEntity.setMovieCategoryAction(rs.getInt("movie_category_Action"));
                MoviedataEntity.setMovieDescription(rs.getString("movie_description"));
                MoviedataEntity.setMovieDirector(rs.getString("movie_director"));
                MoviedataEntity.setMovieCast(rs.getString("movie_cast"));

                movies.add(MoviedataEntity);

            }

            return new MoviedataResponse(0, "成功", movies);

        } catch (SQLException e) {
            return new MoviedataResponse(e.getErrorCode(), e.getMessage(), null);

        } catch (ClassNotFoundException e) {
            return new MoviedataResponse(1, "無法註冊驅動程式", null);

        }
    }
}