package ua.step.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.step.recipes.dtos.UserSignUpDto;
import ua.step.recipes.models.RecipeUser;
import ua.step.recipes.services.UserService;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        return "user";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<RecipeUser> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, UserSignUpDto userDto) {

        if(!Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
            model.addAttribute("message", "Password isn't same as confirm password");
            return "signup";
        }

//        if(userService.exist(userDto.getUsername())) {
//            model.addAttribute("message", "Username is already taken");
//            return "signup";
//        }

        RecipeUser user = new RecipeUser();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        userService.saveUser(user);

        return "redirect:/users";
    }
}
