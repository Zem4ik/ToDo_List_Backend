package ru.zem4ik.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zem4ik.todo.data.ListRepository;
import ru.zem4ik.todo.data.TaskRepository;
import ru.zem4ik.todo.domain.Task;
import ru.zem4ik.todo.domain.TasksList;
import ru.zem4ik.todo.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ListsController {

    private final ListRepository listRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ListsController(ListRepository listRepository, TaskRepository taskRepository) {
        this.listRepository = listRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String mainPage(Model model, @AuthenticationPrincipal User user) {
        Map<Long, List<Task>> listToTasks = new HashMap<>();
        List<TasksList> lists = listRepository.findByUsers(user);
        for (TasksList list : lists) {
            List<Task> tasks = taskRepository.findByList(list);
            listToTasks.put(list.getId(), tasks);
        }
        model.addAttribute("lists", lists);
        model.addAttribute("tasksMap", listToTasks);
        return "taskList";
    }

}
