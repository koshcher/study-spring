package ua.step.recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.step.recipes.models.RecipeUser;
import ua.step.recipes.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<RecipeUser> maybeUser = userRepository.getRecipeUserByLogin(login);
        if (maybeUser.isEmpty()) throw new UsernameNotFoundException(login);
        RecipeUser user = maybeUser.get();
        return new User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }

    public Long saveUser(RecipeUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public List<RecipeUser> getUsers() {
        return userRepository.findAll();
    }
}
