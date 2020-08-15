package com.leanne.edu.springboot.todo.controller;

import com.leanne.edu.springboot.todo.model.Todo;
import com.leanne.edu.springboot.todo.repository.JpaTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    JpaTodoRepository jpaTodoRepository;

    @RequestMapping({"/", "/main"})
    public String index(Model model) {
        // todo add service layer
        List<Todo> todoList = jpaTodoRepository.findAllByOrderByTodoIdAsc();
        model.addAttribute("todolist", todoList);
        return "index";
    }
}
