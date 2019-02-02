package ru.zem4ik.todo.data;

import  org.springframework.data.repository.CrudRepository;
import ru.zem4ik.todo.domain.Task;
import ru.zem4ik.todo.domain.TasksList;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByList(TasksList tasksList);

}
