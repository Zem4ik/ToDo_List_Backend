package ru.zem4ik.todo.web.register;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zem4ik.todo.domain.User;

@Data
class RegistrationForm {

    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String email;

    User toUser(PasswordEncoder passwordEncoder) {
        User user = new User(username, passwordEncoder.encode(password));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        return user;
    }

}
