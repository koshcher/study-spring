package ua.step.security.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ua.step.security.model.User;
import ua.step.security.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean exist(String username) {
        User user = User.builder().username(username).build();
        Example<? extends User> example = Example.of(user);
        return userRepository.exists(example);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
