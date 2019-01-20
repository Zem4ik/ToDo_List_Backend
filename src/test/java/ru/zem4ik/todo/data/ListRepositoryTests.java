package ru.zem4ik.todo.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zem4ik.todo.domain.List;
import ru.zem4ik.todo.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ListRepositoryTests {
    final static String NAME = "LIST_NAME";
    final static byte[] ICON = null;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ListRepository listRepository;

    static List createDefaultList() {
        List list = new List();
        list.setName(NAME);
        list.setIcon(ICON);
        return list;
    }

    @Test
    public void contextLoads() {}

    @Test
    public void testAddingListToUser() {
        User testUser = UserRepositoryTests.createDefaultUser();
        List testList = createDefaultList();

        testUser = userRepository.save(testUser);
        testUser.getLists().add(testList);
        testList.getUsers().add(testUser);
        testUser = userRepository.save(testUser);

        List returnedList = listRepository.findByUsers_Username(testUser.getUsername());

        Assert.assertEquals(testList, returnedList);
    }
}
