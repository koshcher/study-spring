package ua.step.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userName);
        return "home";
    }

    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    @GetMapping("/moderator")
    public String getModerator() {
        return "moderator";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }
}
