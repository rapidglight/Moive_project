package com.example.movie_project.controller;

import com.example.movie_project.model.UserWatchingListEntity;
import com.example.movie_project.model.UserWatchingListResponse;
import jakarta.servlet.http.HttpSession;
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
public class UserWatchingListController {
    @RequestMapping(value = "/userwatchlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    public UserWatchingListResponse movies(String username, HttpSession httpSession) {
        return getuserwatchingList(username);
    }

    private UserWatchingListResponse getuserwatchingList(String username) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=0000");

            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from user_watch_list where user_account='" + username + "'");

            ArrayList<UserWatchingListEntity> movies = new ArrayList<>();

            while (rs.next()) {
                UserWatchingListEntity userWatchingListEntity = new UserWatchingListEntity();
                userWatchingListEntity.setUserWatchMovieName(rs.getString("user_watch_movie_name"));

                movies.add(userWatchingListEntity);

            }

            return new UserWatchingListResponse(0, "成功", movies);

        } catch (SQLException e) {
            return new UserWatchingListResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (ClassNotFoundException e) {
            return new UserWatchingListResponse(1, "無法註冊驅動程式", null);
        }

    }

}
