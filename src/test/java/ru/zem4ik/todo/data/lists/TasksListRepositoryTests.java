package ru.zem4ik.todo.data.lists;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zem4ik.todo.data.ListRepository;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.data.users.UserUtils;
import ru.zem4ik.todo.domain.TasksList;
import ru.zem4ik.todo.domain.User;

import java.util.List;

import static ru.zem4ik.todo.data.lists.ListUtils.createDefaultList;
import static ru.zem4ik.todo.data.users.UserUtils.createDefaultUser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TasksListRepositoryTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ListRepository listRepository;

    @Test
    public void contextLoads() {}

    private TasksList getFirstList(User user) {
        return user.getLists().iterator().next();
    }

    @Test
    public void testAddingListToUser() {
        User testUser = createDefaultUser();
        TasksList testTasksList = createDefaultList();

        testUser = userRepository.save(testUser);
        testUser.getLists().add(testTasksList);

        testUser = userRepository.save(testUser);
        testTasksList = getFirstList(testUser);

        TasksList returnedTaskList = listRepository.findByUsers(testUser).get(0);

        Assert.assertEquals(testTasksList, returnedTaskList);
    }

    @Test
    public void testSharingList() {
        User user1 = UserUtils.generateNext();
        User user2 = UserUtils.generateNext();
        TasksList sharedTasksList = ListUtils.generateNext();

        user1.getLists().add(sharedTasksList);
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        sharedTasksList = user1.getLists().iterator().next();


        user2.getLists().add(sharedTasksList);
        user2 = userRepository.save(user2);

        Assert.assertEquals(sharedTasksList, getFirstList(user1));
        Assert.assertEquals(sharedTasksList, getFirstList(user2));
    }
}
