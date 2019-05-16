package com.leanne.edu.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Todo")
@Setter
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String work;
    Date registration;
    Date modify;
    Date complete;
    String reference;
}
