package ru.zem4ik.todo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String name;
    private final String icon;

}
