package com.example.movie_project.controller;

import com.example.movie_project.model.BaseResponse;
import com.example.movie_project.model.MovieratingEntity;
import com.example.movie_project.model.MovieratingResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class MovieratingController {
    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    public MovieratingResponse movies(String user_account, String user_rating_movie_name) {
        return getMovieratingList(user_account, user_rating_movie_name);
    }

    @RequestMapping(value = "/movieratings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
            public BaseResponse updateMovierating(@RequestBody MovieratingEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=0000");
            stmt = conn.prepareStatement(
                    "INSERT INTO user_rating_list (user_account, user_rating_movie_stars, user_rating_movie_name) " +
                            "VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "user_rating_movie_stars = VALUES(user_rating_movie_stars)");

            stmt.setString(1, data.getUserAccount());
            stmt.setInt(2, data.getUserRatingMovieStars());
            stmt.setString(3, data.getUserRatingMovieName());

            int rowsAffected = stmt.executeUpdate();

            return new BaseResponse(0, "更新成功");
        } catch (SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        } catch (ClassNotFoundException e) {
            return new BaseResponse(1, "無法註冊驅動程式");
        }
    }

    private MovieratingResponse getMovieratingList(String user_account, String user_rating_movie_name) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM user_rating_list WHERE user_account = '" + user_account
                    + "' AND user_rating_movie_name = '" + user_rating_movie_name + "'");// 這裡後續要修改資料庫路徑以及要修改的項目
            stmt = conn.createStatement();
            ArrayList<MovieratingEntity> movies = new ArrayList<>();
            while (rs.next()) {
                MovieratingEntity MovieratingEntity = new MovieratingEntity();
                MovieratingEntity.setRatinglistId(rs.getInt("rating_list_id"));
                MovieratingEntity.setUserAccount(rs.getString("user_account"));
                MovieratingEntity.setUserRatingMovieComments(rs.getString("user_rating_movie_comments"));
                MovieratingEntity.setUserRatingMovieName(rs.getString("user_rating_movie_name"));
                MovieratingEntity.setUserRatingMovieStars(rs.getInt("user_rating_movie_stars"));
                movies.add(MovieratingEntity);
            }
            return new MovieratingResponse(0, "成功", movies);
        } catch (SQLException e) {
            return new MovieratingResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new MovieratingResponse(1, "無法註冊驅動程式", null);
        }
    }
}
