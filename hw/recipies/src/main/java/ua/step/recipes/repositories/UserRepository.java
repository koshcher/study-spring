package ua.step.recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.step.recipes.models.RecipeUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RecipeUser, Long> {
    Optional<RecipeUser> getRecipeUserByLogin(String login);
}
