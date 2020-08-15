package com.leanne.edu.springboot.todo.controller;

import com.leanne.edu.springboot.todo.model.Todo;
import com.leanne.edu.springboot.todo.repository.JpaTodoRepository;
import com.leanne.edu.springboot.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {
    @Autowired
    JpaTodoRepository jpaTodoRepository;

    @Autowired
    TodoService todoService;

    @PostMapping("/add-todo")
    public Todo addWork(@RequestBody String contents, @RequestBody String reference) {
        return todoService.addTodo(contents, reference);
    }

    @RequestMapping(value = "/modify-todo", method = RequestMethod.PUT)
    public Todo modifyTodo(@RequestBody Todo todo) {
        return todoService.updateContents(todo.getTodoId(), todo.getContents());
    }

    @RequestMapping(value = "/complete-todo/{todoId}", method = RequestMethod.PUT)
    public Todo completeTodo(@PathVariable Integer todoId) {
        return todoService.updateCompleteYn(todoId);
    }
}
