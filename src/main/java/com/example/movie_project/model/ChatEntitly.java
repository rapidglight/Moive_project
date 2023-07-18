package com.example.movie_project.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ChatEntitly {
    int id;
    String name;
    String category;
    Date buyDate;
    Date expDate;
    int quantity;
}
