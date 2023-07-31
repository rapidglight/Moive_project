//搜尋API

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

import com.example.movie_project.model.MovieStringArrayResponse;

@RestController
@RequestMapping("/moviealldata")
// 設定前面的路徑(moviealldata/searchmovie)
public class SearchMovieController {
    @RequestMapping(value = "/searchmovie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieStringArrayResponse getMovieName(String keyword) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery("select movie_name from movie where movie_name like '%" + keyword + "%'");

            ArrayList<String> data = new ArrayList<>();
            while (rs.next()) {

                data.add(rs.getString("movie_name"));
            }
            return new MovieStringArrayResponse(0, "成功", data);

        } catch (SQLException e) {
            return new MovieStringArrayResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new MovieStringArrayResponse(1, "無法註冊驅動程式", null);

        }

    }

}
