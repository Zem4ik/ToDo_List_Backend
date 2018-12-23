package ru.zem4ik.todo.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zem4ik.todo.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        final String username = "username";
        final String password = "password";
        final String name = "name";
        final String surname = "surname";
        final String email = "email";
        final String phoneNumber = "phoneNumber";
        User testUser = new User(username,
                password,
                name,
                surname,
                email,
                phoneNumber);
        userRepository.save(testUser);

        User returnedUser = userRepository.findByUsername(username);

        System.out.println(returnedUser);

        Assert.assertEquals(testUser, returnedUser);
    }

}
