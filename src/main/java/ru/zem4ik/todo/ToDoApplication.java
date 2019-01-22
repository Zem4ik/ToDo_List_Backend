package ru.zem4ik.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.zem4ik.todo.data.ListRepository;
import ru.zem4ik.todo.data.TaskRepository;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.domain.List;
import ru.zem4ik.todo.domain.Task;
import ru.zem4ik.todo.domain.User;

@SpringBootApplication
public class ToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner dataLoader(UserRepository userRepository, ListRepository listRepository,
                                        TaskRepository taskRepository) {
        return args -> {
            User user = new User("todo", "todo");
            List list = new List("testList");
            user.getLists().add(list);
            user = userRepository.save(user);
            list = user.getLists().iterator().next();
            Task task = new Task(list, "first task");
            taskRepository.save(task);
            User user2 = new User("todo2", "todo2");
            userRepository.save(user2);
        };
    }

}
