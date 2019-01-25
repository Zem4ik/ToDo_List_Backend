package ru.zem4ik.todo.web.registration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zem4ik.todo.data.UserRepository;
import ru.zem4ik.todo.domain.User;

import javax.validation.Valid;

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
    public String registrationTemplate() {
        return "registration";
    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @PostMapping
    public String processRegistration(@RequestParam(name = "image") MultipartFile image, @Valid RegistrationForm form,
                                      BindingResult bindingResult) {
        User user = form.toUser(passwordEncoder);

        //todo saving images via StorageService

        //check if username is taken
        User registratedUser = userRepository.findByUsername(user.getUsername());
        if (registratedUser == null) {
            userRepository.save(user);
        } else {
            bindingResult.rejectValue("username", null, "That username is taken.");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }


        return "redirect:/login";
    }

}
