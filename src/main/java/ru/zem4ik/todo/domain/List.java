package ru.zem4ik.todo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"users", "tasks"})
@ToString(exclude = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;
    private byte[] icon;

    @ManyToMany(mappedBy = "lists")
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "list")
    private Set<Task> tasks = new HashSet<>();

}
