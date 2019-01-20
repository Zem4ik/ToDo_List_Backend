package ru.zem4ik.todo.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zem4ik.todo.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    final static String USERNAME = "username";
    final static String PASSWORD = "password";
    final static String NAME = "name";
    final static String SURNAME = "surname";
    final static String EMAIL = "email";
    final static byte[] IMAGE = null;
    

    @Autowired
    UserRepository userRepository;

    static User createDefaultUser() {
        return new User(USERNAME,
                PASSWORD,
                NAME,
                SURNAME,
                EMAIL,
                IMAGE);
    }

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
