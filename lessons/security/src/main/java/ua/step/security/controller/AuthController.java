package ua.step.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.step.security.dto.UserSignUpDto;
import ua.step.security.model.User;
import ua.step.security.service.UserService;

import java.util.Objects;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, @RequestBody UserSignUpDto userDto) {

        if(!Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
            model.addAttribute("message", "Password isn't same as confirm password");
            return "signup";
        }

        if(userService.exist(userDto.getUsername())) {
            model.addAttribute("message", "Username is already taken");
            return "signup";
        }

        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        userService.save(user);

        return "redirect: /user";
    }
}
