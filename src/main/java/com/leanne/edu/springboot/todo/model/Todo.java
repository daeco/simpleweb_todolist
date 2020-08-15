package com.leanne.edu.springboot.todo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
@NoArgsConstructor
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int todoId;

    private String contents;

    @Column(nullable = false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private LocalDateTime updatedDatetime;

    @ColumnDefault(value="'N'")
    private String completeYn;

    private String reference;
}