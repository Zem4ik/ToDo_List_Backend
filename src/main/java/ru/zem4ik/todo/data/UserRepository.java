package ru.zem4ik.todo.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import ru.zem4ik.todo.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Nullable
    User findByUsername(String username);

}
