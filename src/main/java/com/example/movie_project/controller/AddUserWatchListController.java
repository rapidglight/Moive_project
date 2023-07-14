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

import com.example.movie_project.model.AddUserWatchListEntity;
import com.example.movie_project.model.AddUserWatchListResponse;
import com.example.movie_project.model.BaseResponse;
import com.example.movie_project.model.MovieratingEntity;
import com.example.movie_project.model.MovieratingResponse;

@RestController
// AddUserWatchListEntity
// AddUserWatchListResponse
// RequestBody required=false
public class AddUserWatchListController {
    // 從資料庫拿資料看看有沒有這筆
    @RequestMapping(value = "/adduserwatchlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddUserWatchListResponse movies(String username, String userWatchMovieName) {
        return getUserWatchList(username, userWatchMovieName);

    }

    private AddUserWatchListResponse getUserWatchList(String username, String userWatchMovieName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?user=root&password=0000");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM user_watch_list WHERE user_account = '" + username
                    + "' AND user_watch_movie_name = '" + userWatchMovieName + "'");// 這裡後續要修改資料庫路徑以及要修改的項目
            stmt = conn.createStatement();
            ArrayList<AddUserWatchListEntity> movies = new ArrayList<>();
            while (rs.next()) {
                AddUserWatchListEntity addUserWatchListEntity = new AddUserWatchListEntity();
                addUserWatchListEntity.setUserAccount(rs.getString("user_account"));
                addUserWatchListEntity.setUserWatchMovieName(rs.getString("user_watch_movie_name"));
                movies.add(addUserWatchListEntity);
            }
            return new AddUserWatchListResponse(0, "成功", movies);
        } catch (SQLException e) {
            return new AddUserWatchListResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new AddUserWatchListResponse(1, "無法註冊驅動程式", null);
        }
    }

    // 新增user的清單到資料庫裡 post

    @RequestMapping(value = "/newuserwatchlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)

    public AddUserWatchListResponse addUserWatchList(@RequestBody AddUserWatchListEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/javaconnect?user=root&password=0000");
            stmt = conn.prepareStatement(
                    "INSERT INTO user_watch_list(watch_list_id,user_account,user_watch_movie_name)"
                            +
                            "VALUES (null,?, ?) ");

            stmt.setString(1, data.getUserAccount());
            stmt.setString(2, data.getUserWatchMovieName());

            stmt.executeUpdate();

            return new AddUserWatchListResponse(0, "更新成功", null);
        } catch (SQLException e) {
            return new AddUserWatchListResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new AddUserWatchListResponse(1, "無法註冊驅動程式", null);
        }
    }

    // 從資料庫裡刪除userwatchlist資料 delete

    @RequestMapping(value = "/deleteuserwatchlist", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, // 傳入的資料格式
            produces = MediaType.APPLICATION_JSON_VALUE)

    public AddUserWatchListResponse deleteUserWatchList(@RequestBody AddUserWatchListEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/javaconnect?user=root&password=0000");
            stmt = conn.prepareStatement(
                    "delete from user_watch_list"
                            + " where user_account=? and user_watch_movie_name=?");

            stmt.setString(1, data.getUserAccount());
            stmt.setString(2, data.getUserWatchMovieName());

            stmt.executeUpdate();

            return new AddUserWatchListResponse(0, "更新成功", null);
        } catch (SQLException e) {
            return new AddUserWatchListResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new AddUserWatchListResponse(1, "無法註冊驅動程式", null);
        }
    }

}
