package ru.zem4ik.todo.data.users;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.domain.User;

import static ru.zem4ik.todo.data.users.UserUtils.createDefaultUser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {}

    @Test
    public void testWriteAndReadUser() {
        User testUser = createDefaultUser();
        final String username = testUser.getUsername();
        userRepository.save(testUser);

        User returnedUser = userRepository.findByUsername(username);

        Assert.assertEquals(testUser, returnedUser);
    }

}
