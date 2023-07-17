//這個API傳入電影的名稱 ，比對是哪個電影 

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
import com.example.movie_project.model.SearchMoviedataEntity;

import com.example.movie_project.model.SearchMoviedataResponse;

@RestController
public class SearchMoviedataController {

    @RequestMapping(value = "/searchmoviedata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public SearchMoviedataResponse movies(String moviename) {
        return SearchgetMoviedataList(moviename);

    }

    private SearchMoviedataResponse SearchgetMoviedataList(String moviename) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        moviename = moviename.replace("\'", "\\\'");
        // moviename = moviename.replace("\\\'", "\'");
        System.out.println(moviename);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from movie where movie_name='" + moviename + "'");

            ArrayList<SearchMoviedataEntity> searchmovies = new ArrayList<>();

            while (rs.next()) {
                SearchMoviedataEntity SearchMoviedataEntity = new SearchMoviedataEntity();
                SearchMoviedataEntity.setMovieId(rs.getInt("movie_id"));
                SearchMoviedataEntity.setMovieName(rs.getString("movie_name"));
                SearchMoviedataEntity.setImageUrl(rs.getString("image_url"));
                SearchMoviedataEntity.setYear(rs.getInt("year"));
                SearchMoviedataEntity.setAge(rs.getString("age"));
                SearchMoviedataEntity.setMovieLong(rs.getInt("movie_long"));
                SearchMoviedataEntity.setMovieCategoryRomance(rs.getInt("movie_category_Romance"));

                SearchMoviedataEntity.setMovieCategoryComedy(rs.getInt("movie_category_Comedy"));
                SearchMoviedataEntity.setMovieCategoryDrama(rs.getInt("movie_category_Drama"));
                SearchMoviedataEntity.setMovieCategoryAction(rs.getInt("movie_category_Action"));
                SearchMoviedataEntity.setMovieDescription(rs.getString("movie_description"));
                SearchMoviedataEntity.setMovieDirector(rs.getString("movie_director"));
                SearchMoviedataEntity.setMovieCast(rs.getString("movie_cast"));
                SearchMoviedataEntity.setMovieTrailer(rs.getString("trailer"));

                searchmovies.add(SearchMoviedataEntity);

            }

            return new SearchMoviedataResponse(0, "成功", searchmovies);

        } catch (SQLException e) {
            return new SearchMoviedataResponse(e.getErrorCode(), e.getMessage(), null);

        } catch (ClassNotFoundException e) {
            return new SearchMoviedataResponse(1, "無法註冊驅動程式", null);

        }
    }
}