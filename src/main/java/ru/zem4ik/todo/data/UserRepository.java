package ru.zem4ik.todo.data;

import org.springframework.data.repository.CrudRepository;
import ru.zem4ik.todo.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
