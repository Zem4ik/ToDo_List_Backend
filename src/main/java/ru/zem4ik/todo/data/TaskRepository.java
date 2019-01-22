package ru.zem4ik.todo.data;

import org.springframework.data.repository.CrudRepository;
import ru.zem4ik.todo.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
