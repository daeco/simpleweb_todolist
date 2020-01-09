package com.leanne.edu.springboot;

import com.leanne.edu.springboot.dao.TodoDAO;
import com.leanne.edu.springboot.model.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(Application.class, args);
    }
}