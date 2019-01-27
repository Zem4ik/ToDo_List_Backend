package ru.zem4ik.todo.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.zem4ik.todo.data.ListRepository;
import ru.zem4ik.todo.data.TaskRepository;
import ru.zem4ik.todo.domain.Task;
import ru.zem4ik.todo.domain.TasksList;
import ru.zem4ik.todo.domain.User;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/task")
@CrossOrigin(origins = "*")
public class TaskApiController {

    private final TaskRepository taskRepository;
    private final ListRepository listRepository;

    @Autowired
    public TaskApiController(TaskRepository taskRepository, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task addTask(@RequestBody TaskRequest taskRequest) {
        Optional listOptional = listRepository.findById(taskRequest.getListId());
        if (!listOptional.isPresent()) {
            throw new RuntimeException("List with id " + taskRequest.getListId() + " doesn't exist!");
        }
        TasksList list = (TasksList) listOptional.get();
        Task task = new Task(list, taskRequest.getTitle());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("taskId") long taskId) {
        try {
            taskRepository.deleteById(taskId);
        } catch (EmptyResultDataAccessException e) {
            //todo use logger here
            System.err.println("MYTAG: task with id " + taskId + " doesn't exist");
        }
    }
}
