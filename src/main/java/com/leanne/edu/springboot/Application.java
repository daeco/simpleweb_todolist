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

        TodoDAO todoDAO = new TodoDAO();
        Todo todo = new Todo();
        todo.setWork("코딩하기");

        todoDAO.add(todo);
        Todo todo2 = todoDAO.get(1);

        System.out.println(todo2.getId());
        System.out.println(todo2.getWork());
        System.out.println(todo2.getRegistration());

        // 데이터 추가
        todoDAO.add(todo2);

        // 데이터 수정
        todoDAO.modify(1, "코딩 안하기", null);
    }
}