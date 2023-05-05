package ua.step.firstrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.step.firstrun.model.User;
import ua.step.firstrun.repository.UserRepository;
import ua.step.firstrun.util.UserNotFoundException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{login}")
    public User getUser(@PathVariable String login) {
        return userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    public Iterable<User> allUser() {
        return userRepository.findAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
