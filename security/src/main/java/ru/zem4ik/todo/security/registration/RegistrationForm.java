package ru.zem4ik.todo.security.registration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zem4ik.todo.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
class RegistrationForm {

    @NotBlank(message = "Username can't be empty!")
    private String username;
    @NotBlank(message = "Password can't be empty!")
    private String password;
    private String name;
    private String surname;
    @Email(message = "Enter valid e-mail")
    private String email;

    User toUser(PasswordEncoder passwordEncoder) {
        User user = new User(username, passwordEncoder.encode(password));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        return user;
    }

}
