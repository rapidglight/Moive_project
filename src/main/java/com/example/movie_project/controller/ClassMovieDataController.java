package com.example.movie_project.controller;

import com.example.movie_project.model.ClassMovieDataEntity;
import com.example.movie_project.model.ClassMovieDataResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ClassMovieDataController {

    @RequestMapping(value = "/classmovie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    public ClassMovieDataResponse movies(String classname) throws JsonProcessingException {
        return getClassMoviedataList(classname);
    }

    private ClassMovieDataResponse getClassMoviedataList(String classname) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=0000");

            stmt = conn.createStatement();
            // 給他一串字串 ex: action,drama 用,號分解
            // classname = "action,drama,romance,comedy";
            String[] moviecategory = classname.split(",");

            if (moviecategory.length == 1) {

                rs = stmt.executeQuery(
                        "select image_url,movie_name,movie_id from movie where movie_category_" + moviecategory[0]
                                + "=1");
                System.out.println("執行成功1");
                System.out.println(moviecategory[0]);
                System.out.println(
                        "select image_url,movie_name,movie_id from movie where movie_category_" + moviecategory[0]
                                + "=1");

            } else if (moviecategory.length == 2) {

                rs = stmt.executeQuery(
                        "select image_url,movie_name,movie_id from movie where movie_category_" + moviecategory[0]
                                + "=1 and "
                                + "movie_category_" + moviecategory[1] + "=1");
                System.out.println("執行成功2");

            } else if (moviecategory.length == 3) {

                rs = stmt.executeQuery(
                        "select image_url,movie_name,movie_id from movie where movie_category_" + moviecategory[0]
                                + "=1 and "
                                + "movie_category_" + moviecategory[1] + "=1 and "
                                + "movie_category_" + moviecategory[2] + "=1");
                System.out.println("執行成功3");

            } else if (moviecategory.length == 4) {

                rs = stmt.executeQuery(
                        "select image_url,movie_name,movie_id from movie where movie_category_" + moviecategory[0]
                                + "=1 and "
                                + "movie_category_" + moviecategory[1] + "=1 and "
                                + "movie_category_" + moviecategory[2] + "=1 and "
                                + "movie_category_" + moviecategory[3] + "=1");
                System.out.println("執行成功4");

            }

            ArrayList<ClassMovieDataEntity> movies = new ArrayList<>();

            while (rs.next()) {
                ClassMovieDataEntity classMovieDataEntity = new ClassMovieDataEntity();
                classMovieDataEntity.setMovieId(rs.getInt("movie_id"));
                classMovieDataEntity.setMovieName(rs.getString("movie_name"));
                classMovieDataEntity.setImageUrl(rs.getString("image_url"));

                movies.add(classMovieDataEntity);

            }

            return new ClassMovieDataResponse(0, "成功", movies);

        } catch (SQLException e) {
            return new ClassMovieDataResponse(e.getErrorCode(), e.getMessage(), null);

        } catch (ClassNotFoundException e) {
            return new ClassMovieDataResponse(1, "無法註冊驅動程式", null);

        }
    }

}
