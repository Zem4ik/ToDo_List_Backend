package ru.zem4ik.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"users", "tasks"})
@ToString(exclude = {"users", "tasks"})
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "lists")
public class TasksList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;
    private String icon;

    @ManyToMany(mappedBy = "lists")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "list")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();

}
