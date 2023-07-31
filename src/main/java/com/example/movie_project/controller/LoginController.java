//這個API 負責帳號登入/登出

package com.example.movie_project.controller;

import com.example.movie_project.model.LoginResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;







@RestController
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    public LoginResponse Login(String username, String password, HttpSession httpSession) {
        LoginResponse loginResponse = checkAccount(username, password);

        if(loginResponse.getCode() == 0) { // 登入成功
            httpSession.setAttribute("loginStatus", username + "已登入");
        } else {
            httpSession.removeAttribute("loginStatus");
        }

        return loginResponse;
    }

    private LoginResponse checkAccount(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 註冊mysql資料庫驅動程式
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 連線資料庫
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaconnect?allowPublicKeyRetrieval=true&useSSL=false&" +
                    "user=root&password=0000");

            // 取得Statement 物件
            stmt = conn.createStatement();

            // 查詢該帳號是否存在

            rs = stmt.executeQuery("SELECT count(*) as c FROM userdata where user_account='" + username + "'");

            // 判斷帳號是否存在
            rs.next();// 跳到查詢結果的第一筆資料
            int c = rs.getInt("c");// 查詢到的資料筆數

            // 帳號不存在，直接返回錯誤
            if (c == 0) {
                // 把資料庫相關資源釋放
                rs.close();
                stmt.close();
                conn.close();
                return new LoginResponse(2, "帳號不存在");
            }

            // 帳號存在，繼續判斷密碼
            rs = stmt.executeQuery(
                    "SELECT count(*) as c FROM userdata where user_account='" + username + "'  and user_password ='"
                            + password + "';");
            // 移動第一筆資料
            rs.next();
            c = rs.getInt("c"); // 查詢到的資料筆數

            // 把資料庫相關資源釋放
            rs.close();
            stmt.close();
            conn.close();

            // 密碼錯誤
            if (c == 0) {
                return new LoginResponse(3, "密碼錯誤");
            }

            return new LoginResponse(0, "登入成功");

            // return c == 0 ? new getActionResult(2, "帳號不存在") : new
            // getActionResult(0,"成功");

        } catch (ClassNotFoundException e) {
            // 無法註冊(錯誤代碼1)
            return new LoginResponse(1, "無法註冊驅動程式");

        } catch (SQLException e) {
            // SQL操作錯誤
            return new LoginResponse(e.getErrorCode(), e.getMessage());
        }

    }

}
