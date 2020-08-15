package com.leanne.edu.springboot.todo.repository;

import com.leanne.edu.springboot.todo.model.Todo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class TodoSpecification {

    public static Specification<Todo> containsContents(String contents) {
        return new Specification<Todo>() {
            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("contents"), "%" + contents + "%");
            }
        };
    }

    public static Specification<Todo> betweenCreatedDatetime(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return new Specification<Todo>() {
            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("createdDatetime"), startDatetime, endDatetime);
            }
        };
    }

    public static Specification<Todo> betweenUpdatedDatetime(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return new Specification<Todo>() {
            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("updatedDatetime"), startDatetime, endDatetime);
            }
        };
    }
}
