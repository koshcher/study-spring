package ua.step.recipes.repositories;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.step.recipes.models.Component;
import ua.step.recipes.models.Recipe;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> getRecipeById(Long id);


    @Query("select recipe from Recipe as recipe " +
            "join Component as component on component.recipe.id = recipe.id " +
            "join Product as product on product.id = component.product.id " +
            "where recipe.name like %:name% and product.id in :products"
    )
    Page<Recipe> findAllByProductsAndName(
            @Param("products") Collection<Long> products, @Param("name") String name,
            Pageable pageable
    );
    Page<Recipe> findAllByNameStartsWith(
            @NonNull String name,
            Pageable pageable
    );
}
