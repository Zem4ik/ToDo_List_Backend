package ru.zem4ik.todo.web.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.domain.User;

import java.io.IOException;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@RequestParam(name = "image") MultipartFile image, RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        //todo saving images
        userRepository.save(user);
        return "redirect:/login";
    }

}
