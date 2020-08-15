package com.leanne.edu.springboot.todo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "WORK_REF")
@IdClass(WorkReference.class)
@NoArgsConstructor
@Data
public class WorkReference implements Serializable {

    @Id
    private int fromId;

    @Id
    private int toId;

    private Date registrationDate;
}
