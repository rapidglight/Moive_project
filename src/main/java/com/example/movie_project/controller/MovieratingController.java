package com.example.movie_project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_project.model.BaseResponse;
import com.example.movie_project.model.MovieratingEntity;
import com.example.movie_project.model.MovieratingResponse;

@RestController
public class MovieratingController {
    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieratingResponse games() {
        return getGameList();
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addGame(@RequestBody MovieratingEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");

            stmt = conn.prepareStatement("INSERT INTO storesystem VALUES(null, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, data.getUserAccount());
            stmt.setString(2, data.getUserRatingMovieComments());
            stmt.setString(3, data.getUserRatingMovieName());
            stmt.setInt(4, data.getUserRatingMovieStars());
            stmt.setInt(5, data.getRatinglistId());

            stmt.executeUpdate();

            return new BaseResponse(0, "新增成功");

        } catch (SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        } catch (ClassNotFoundException e) {
            return new BaseResponse(1, "無法註冊驅動程式");
        }
    }

    @RequestMapping(value = "/game", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateGame(@RequestBody MovieratingEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnent?user=root&password=0000");

            stmt = conn.prepareStatement(
                    "UPDATE user_rating_list SET  user_rating_moviestars=? WHERE id=?");
            stmt.setInt(4, data.getUserRatingMovieStars());
            stmt.setInt(5, data.getRatinglistId());

            stmt.executeUpdate();

            return new BaseResponse(0, "更新成功");

        } catch (SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        } catch (ClassNotFoundException e) {
            return new BaseResponse(1, "無法註冊驅動程式");
        }
    }

    private MovieratingResponse getGameList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user_rating_list");// 這裡後續要修改資料庫路徑以及要修改的項目

            ArrayList<MovieratingEntity> games = new ArrayList<>();
            while (rs.next()) {
                MovieratingEntity MovieratingEntity = new MovieratingEntity();
                MovieratingEntity.setRatinglistId(rs.getInt("id"));
                MovieratingEntity.setUserAccount(rs.getString("user_account"));
                MovieratingEntity.setUserRatingMovieComments(rs.getString("user_rating_movie_comments"));
                MovieratingEntity.setUserRatingMovieName(rs.getString("user_rating_movie_name"));
                MovieratingEntity.setUserRatingMovieStars(rs.getInt("user_rating_movie_stars"));
                games.add(MovieratingEntity);
            }
            return new MovieratingResponse(0, "成功", games);
        } catch (SQLException e) {
            return new MovieratingResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new MovieratingResponse(1, "無法註冊驅動程式", null);
        }
    }
}
