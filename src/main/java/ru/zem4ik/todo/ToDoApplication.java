package ru.zem4ik.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zem4ik.todo.data.TaskRepository;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.domain.TasksList;
import ru.zem4ik.todo.domain.Task;
import ru.zem4ik.todo.domain.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner dataLoader(UserRepository userRepository, TaskRepository taskRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User("todo", passwordEncoder.encode("todo"));

            List<TasksList> lists = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                TasksList list =new TasksList("testList" + i);
                lists.add(list);
            }
            user.getLists().addAll(lists);
            user = userRepository.save(user);
            lists = new ArrayList<>(user.getLists());
            for (TasksList list : lists) {
                for (int i = 0; i < 2; ++i) {
                    list.getTasks().add(new Task(list, list.getName() + "_testTask" + i));
                }
                taskRepository.saveAll(list.getTasks());
            }

            User user2 = new User("test", passwordEncoder.encode("test"));
            userRepository.save(user2);
        };
    }

}
