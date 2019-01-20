package ru.zem4ik.todo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "users")
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private byte[] icon;

    @ManyToMany(mappedBy = "lists")
    private Set<User> users = new HashSet<>();

}
