package ru.zem4ik.todo.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zem4ik.todo.domain.User;

@Controller
@RequestMapping("/user_info")
public class UserInfoController {

    @GetMapping
    public String userInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user_info";
    }

}
