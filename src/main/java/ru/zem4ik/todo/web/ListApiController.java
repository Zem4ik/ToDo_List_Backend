package ru.zem4ik.todo.web;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.zem4ik.todo.data.ListRepository;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.domain.TasksList;
import ru.zem4ik.todo.domain.User;

@RestController
@RequestMapping(path = "api/list")
public class ListApiController {

    private final UserRepository userRepository;
    private final ListRepository listRepository;

    public ListApiController(UserRepository userRepository, ListRepository listRepository) {
        this.userRepository = userRepository;
        this.listRepository = listRepository;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TasksList addList(@AuthenticationPrincipal User user,
                             @RequestBody ListRequest listRequest) {
        TasksList list = new TasksList(listRequest.getName());
        list = listRepository.save(list);

        user = userRepository.findById(user.getId()).get();
        user.getLists().add(list);
        userRepository.save(user);

        return list;
    }

    @DeleteMapping(path = "/{listId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteList(@PathVariable("listId") long listId) {
        try {
            listRepository.deleteById(listId);
        } catch (EmptyResultDataAccessException e) {
            //todo use logger here
            System.err.println("MYTAG: list with id " + listId + " doesn't exist");
        }
    }
}
