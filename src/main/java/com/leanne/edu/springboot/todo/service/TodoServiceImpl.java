package com.leanne.edu.springboot.todo.service;

import com.leanne.edu.springboot.todo.model.Todo;
import com.leanne.edu.springboot.todo.repository.JpaTodoRepository;
import com.leanne.edu.springboot.todo.repository.TodoSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    JpaTodoRepository jpaTodoRepository;

    @Override
    public List<Todo> getTodoAll() {
        List<Todo> todoList = jpaTodoRepository.findAllByOrderByTodoIdAsc();
        return todoList;
    }

    @Override
    public Todo getTodoById(int todoId) {
        Todo todo = jpaTodoRepository.findById(todoId).get();
        return todo;
    }

    @Override
    public Todo addTodo(String contents, String reference) {
        Todo todo = new Todo();
        todo.setContents(contents);
        todo.setReference(reference);
        return jpaTodoRepository.save(todo);
    }

    @Override
    public Todo updateContents(int todoId, String contents) {
        Todo todo = jpaTodoRepository.findById(todoId).get();
        todo.setContents(contents);
        todo.setCreatedDatetime(LocalDateTime.now());
        Todo resultTodo = jpaTodoRepository.save(todo);
        if(!resultTodo.equals(todo)) {
            log.error("fail updateContents");
        }
        return todo;
    }

    @Override
    public Todo updateCompleteYn(int todoId) {
        Todo todo = jpaTodoRepository.findById(todoId).get();
        List<Todo> todoList = jpaTodoRepository.findByReferenceContainsAndCompleteYn(todoId + "", "N");
        if(todoList.size() > 0) {
            log.error("fail updateCompleteYn : exist references");
        }
        else {
            todo.setCompleteYn("Y");
            Todo resultTodo = jpaTodoRepository.save(todo);
            if (!resultTodo.getCompleteYn().equals("Y")) {
                log.error("fail updateCompleteYn");
            }
        }
        return todo;
    }

    public List<Todo> searchTodoList(LocalDateTime startCreatedDatetime, LocalDateTime endCreatedDatetime,
                                                                             LocalDateTime startUpdatedDatetime, LocalDateTime endUpdatedDatetime,
                                                                             String contents) {

        Specification<Todo> spec = Specification.where(TodoSpecification.containsContents(contents));
        if(startCreatedDatetime != null && endCreatedDatetime != null) {
            spec = spec.and(TodoSpecification.betweenCreatedDatetime(startCreatedDatetime, endCreatedDatetime));
        }
        if(startUpdatedDatetime != null && endUpdatedDatetime != null) {
            spec = spec.and(TodoSpecification.betweenUpdatedDatetime(startUpdatedDatetime, endUpdatedDatetime));
        }

        return jpaTodoRepository.findAll(spec);
    }



    // todo move to Common Class
    public List<Integer> convertToIntegerList(String[] strings) {
        List<Integer> list = new ArrayList<>();
        for(String num : strings) {
            list.add(Integer.parseInt(num));
        }
        return list;
    }
}
