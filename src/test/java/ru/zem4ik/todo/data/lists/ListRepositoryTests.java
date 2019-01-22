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
import ru.zem4ik.todo.domain.List;
import ru.zem4ik.todo.domain.User;

import static ru.zem4ik.todo.data.lists.ListUtils.createDefaultList;
import static ru.zem4ik.todo.data.users.UserUtils.createDefaultUser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ListRepositoryTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ListRepository listRepository;

    @Test
    public void contextLoads() {}

    private List getFirstList(User user) {
        return user.getLists().iterator().next();
    }

    @Test
    public void testAddingListToUser() {
        User testUser = createDefaultUser();
        List testList = createDefaultList();

        testUser = userRepository.save(testUser);
        testUser.getLists().add(testList);

        testUser = userRepository.save(testUser);
        testList = getFirstList(testUser);

        List returnedList = listRepository.findByUsers_Username(testUser.getUsername());

        Assert.assertEquals(testList, returnedList);
    }

    @Test
    public void testSharingList() {
        User user1 = UserUtils.generateNext();
        User user2 = UserUtils.generateNext();
        List sharedList = ListUtils.generateNext();

        user1.getLists().add(sharedList);
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        sharedList = user1.getLists().iterator().next();


        user2.getLists().add(sharedList);
        user2 = userRepository.save(user2);

        Assert.assertEquals(sharedList, getFirstList(user1));
        Assert.assertEquals(sharedList, getFirstList(user2));
    }
}
