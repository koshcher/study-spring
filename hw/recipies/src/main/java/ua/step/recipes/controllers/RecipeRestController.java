package ua.step.recipes.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.step.recipes.dtos.RecipeDto;
import ua.step.recipes.models.Recipe;
import ua.step.recipes.models.ResourceNotFoundException;
import ua.step.recipes.repositories.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*

		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.7.0</version>
		</dependency>

*/

@Tag(name = "Recipes", description = "do recipes")
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeRestController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeDto> getAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(r -> new RecipeDto(
                        r.getId(), r.getName(), r.getDescription(),
                        r.getUrl(), r.getCreated(), r.getUpdated())
                )
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe getOne(@PathVariable Long id) {
        return recipeRepository.getRecipeById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Recipe recipe) {
        recipe.setCreated(LocalDateTime.now());
        recipe.setUpdated(LocalDateTime.now());
        recipeRepository.save(recipe);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void update(@RequestBody Recipe recipe) {
        Optional<Recipe> dbRecipe = recipeRepository
                .getRecipeById(recipe.getId());

        dbRecipe.ifPresent(value ->  {
            value.setName(recipe.getName());
            value.setUpdated(LocalDateTime.now());
        });
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
    }
}
