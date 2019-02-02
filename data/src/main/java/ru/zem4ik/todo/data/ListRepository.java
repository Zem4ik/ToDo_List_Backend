package ru.zem4ik.todo.data;

import org.springframework.data.repository.CrudRepository;
import ru.zem4ik.todo.domain.TasksList;
import ru.zem4ik.todo.domain.User;

import java.util.List;

public interface ListRepository extends CrudRepository<TasksList, Long> {

    List<TasksList> findByUsers(User user);

}
