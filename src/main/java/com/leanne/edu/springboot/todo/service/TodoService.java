package com.leanne.edu.springboot.todo.service;

import com.leanne.edu.springboot.todo.model.Todo;

import java.util.List;

public interface TodoService {

    public List<Todo> getTodoAll();

    public Todo getTodoById(int todoId);

    public Todo addTodo(String contents, String reference);

    public Todo updateContents(int todoId, String contents);

    public Todo updateCompleteYn(int todoId);
}
