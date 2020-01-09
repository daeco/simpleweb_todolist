package com.leanne.edu.springboot.controller;

import com.leanne.edu.springboot.dao.TodoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Controller
public class MainController {
    @Autowired
    TodoDAO todoDAO;

    @PostConstruct
    public void init() {
        try {
            todoDAO.insertTodo("집안일", null);
            todoDAO.insertTodo("짤래", "1");
            todoDAO.insertTodo("청서", "1");
            todoDAO.insertTodo("방청소", "1|3");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
