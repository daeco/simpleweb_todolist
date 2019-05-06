package com.leanne.edu.springboot;

import com.leanne.edu.springboot.dao.UserDAO;
import com.leanne.edu.springboot.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(Application.class, args);

        UserDAO dao = new UserDAO();
        User user = new User();
        user.setId("whatsup");
        user.setName("갓준형");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공");
    }
}