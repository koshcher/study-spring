package ua.step.recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.step.recipes.models.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
}
