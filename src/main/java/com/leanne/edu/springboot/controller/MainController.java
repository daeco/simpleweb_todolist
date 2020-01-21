package com.leanne.edu.springboot.controller;

import com.leanne.edu.springboot.dao.TodoDAO;
import com.leanne.edu.springboot.dao.WorkReferenceDAO;
import com.leanne.edu.springboot.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    TodoDAO todoDAO;
    
    @Autowired
    WorkReferenceDAO workReferenceDAO;

    @PostConstruct
    public void init() {
        try {
            todoDAO.insertTodo(1, "집안일", null);
            todoDAO.insertTodo(2,"빨래", null);
            workReferenceDAO.insertReference(2, 1);
            todoDAO.insertTodo(3,"청소", null);
            workReferenceDAO.insertReference(3, 1);
            todoDAO.insertTodo(4,"방청소", null);
            workReferenceDAO.insertReference(4, 1);
            workReferenceDAO.insertReference(4, 3);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/home")
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

    @RequestMapping(value = "/add-work", method = RequestMethod.POST)
    public String addWork(@RequestParam String work, @RequestParam Integer reference) {
        try {
            Integer id = todoDAO.selectNextTodoId();
            if(id != null) {
                todoDAO.insertTodo(id, work, null);
                workReferenceDAO.insertReference(reference, id);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }
}
