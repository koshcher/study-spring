package rk.security3.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import rk.security3.model.User;
import rk.security3.repository.UserRepository;


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
