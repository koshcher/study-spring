package rk.security3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rk.security3.dto.UserSignUpDto;
import rk.security3.model.User;
import rk.security3.service.UserService;

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
