package ru.zem4ik.todo.web.register;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zem4ik.todo.domain.User;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String email;
    private final byte[] image;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                name, surname, email, image);
    }

}
