package com.leanne.edu.springboot.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leanne.edu.springboot.dao.TodoDAO;
import com.leanne.edu.springboot.model.Todo;

@Controller
public class MainController {
    @Autowired
    TodoDAO todoDAO;
    
    

    @PostConstruct
    public void init() {
        try {
            todoDAO.insertTodo("집안일", null);
            todoDAO.insertTodo("빨래", null);
            todoDAO.insertTodo("청소", null);
            todoDAO.insertTodo("방청소", null);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Todo> todoList = new ArrayList<>();
        try {
            todoList = todoDAO.selectAll();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        model.addAttribute("todolist", todoList);

        return "index";
    }
    
    @PostMapping
    public ResponseEntity<?> addWork(@Valid @RequestBody Todo todo, Errors errors) {
    	
    	return ResponseEntity.ok(null);
    }
}
