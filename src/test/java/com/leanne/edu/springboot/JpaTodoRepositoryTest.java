package com.leanne.edu.springboot;

import com.leanne.edu.springboot.todo.repository.JpaTodoRepository;
import com.leanne.edu.springboot.todo.model.Todo;

import com.leanne.edu.springboot.todo.repository.TodoSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class JpaTodoRepositoryTest {
    @Autowired
    private JpaTodoRepository jpaTodoRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void findIdTest() {
        int todoId = 1;
        Todo todo = jpaTodoRepository.findById(todoId).get();
        log.info("todo : {}", todo);
    }

    @Test
    public void findAllTest() {
        int todoId = 1;
        List<Todo> todoList = jpaTodoRepository.findAllByOrderByTodoIdAsc();
        log.info("todo : {}", todoList);
    }

    @Test
    public void saveTest() {
        Todo todo = new Todo();
        todo.setContents("거실 청소");
        jpaTodoRepository.save(todo);

        List<Todo> todoList = jpaTodoRepository.findAllByOrderByTodoIdAsc();
        log.info("todo : {}", todoList);
    }

    @Test
    public void updateTest() {
        int todoId = 1;
        Todo todo = jpaTodoRepository.findById(todoId).get();
        todo.setContents("집안일 -> 수정");
        jpaTodoRepository.save(todo);

        List<Todo> todoList = jpaTodoRepository.findAllByOrderByTodoIdAsc();
        log.info("todo : {}", todoList);
    }

    @Test
    public void deleteTest() {
        jpaTodoRepository.deleteById(1);
        List<Todo> todoList = jpaTodoRepository.findAllByOrderByTodoIdAsc();
        log.info("todo : {}", todoList);
    }

    @Test
    public void specificationTest() {
        String contents = "소";
        String startDate = "2020-04-01 00:00:00";
        String endDate = "2020-08-15 23:59:59";
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

        Specification<Todo> spec = Specification.where(TodoSpecification.containsContents(contents));
        spec = spec.and(TodoSpecification.betweenCreatedDatetime(startDateTime, endDateTime));

        List<Todo> todoList = jpaTodoRepository.findAll(spec);

        log.info("todo : {}", todoList);
    }
}