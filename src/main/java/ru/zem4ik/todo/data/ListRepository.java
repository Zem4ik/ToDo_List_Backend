package ru.zem4ik.todo.data;

import org.springframework.data.repository.CrudRepository;
import ru.zem4ik.todo.domain.List;

public interface ListRepository extends CrudRepository<List, Long> {

    java.util.List<List> findAll();

    List findByUsers_Username(String username);

}
