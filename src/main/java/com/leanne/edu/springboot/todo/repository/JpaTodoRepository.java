package com.leanne.edu.springboot.todo.repository;

import com.leanne.edu.springboot.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaTodoRepository extends JpaRepository<Todo, Integer>, JpaSpecificationExecutor<Todo> {
    List<Todo> findAllByOrderByTodoIdAsc();

    //@Query("SELECT * FROM todo WHERE todo_id IN = :todoIdList")
    List<Todo> findByTodoIdIn(List<Integer> todoIdList);

    List<Todo> findByReferenceContainsAndCompleteYn(String referId, String completeYn);
}
