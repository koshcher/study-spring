package ua.step.firstrun.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.step.firstrun.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
     Optional<User> findByLogin(String login);
}
