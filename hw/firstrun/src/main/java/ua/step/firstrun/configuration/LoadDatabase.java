package ua.step.firstrun.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.step.firstrun.model.User;
import ua.step.firstrun.repository.UserRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
       return  args -> {
           userRepository.save(new User("user1", "user1", User.Sex.Female));
           userRepository.save(new User("user2", "user2", User.Sex.Male));
           userRepository.save(new User("user3", "user3", User.Sex.Female));
       };
    }
}
