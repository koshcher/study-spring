package ua.step.recipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.step.recipes.models.Component;
import ua.step.recipes.models.Product;
import ua.step.recipes.models.Recipe;
import ua.step.recipes.models.RecipeUser;
import ua.step.recipes.repositories.ProductRepository;
import ua.step.recipes.repositories.RecipeRepository;
import ua.step.recipes.services.UserService;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class InitDatabase {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            initUsers();
            initProducts();
        };
    }

    public void initUsers() {
        RecipeUser recipeUser = new RecipeUser();
        recipeUser.setLogin("user");
        recipeUser.setPassword("user");
        recipeUser.setId(1L);
        recipeUser.setRoles(Collections.singletonList("admin"));
        userService.saveUser(recipeUser);
    }

    public void initProducts() {
        Product eggs = productRepository.save(new Product("eggs"));
        Product flour = productRepository.save(new Product("flour"));
        Product water = productRepository.save(new Product("water"));
        Product sugar = productRepository.save(new Product("sugar"));


        recipeRepository.save(new Recipe("borscht", "mmmm", Arrays.asList(
                new Component(500, Component.Unit.Gram, flour),
                new Component(10, Component.Unit.Liter, water),
                new Component(5, Component.Unit.Gram, sugar)
        )));
        recipeRepository.save(new Recipe("sup", "aaa", Arrays.asList(
                new Component(10, Component.Unit.Liter, water),
                new Component(5, Component.Unit.Gram, sugar),
                new Component(2, Component.Unit.Piece, eggs)
        )));


        recipeRepository.save(new Recipe("borscht", "mmmm", Arrays.asList(
                new Component(500, Component.Unit.Gram, flour),
                new Component(10, Component.Unit.Liter, water),
                new Component(5, Component.Unit.Gram, sugar)
        )));
        recipeRepository.save(new Recipe("sup", "aaa", Arrays.asList(
                new Component(10, Component.Unit.Liter, water),
                new Component(5, Component.Unit.Gram, sugar),
                new Component(2, Component.Unit.Piece, eggs)
        )));


        recipeRepository.save(new Recipe("borscht", "mmmm", Arrays.asList(
                new Component(500, Component.Unit.Gram, flour),
                new Component(10, Component.Unit.Liter, water),
                new Component(5, Component.Unit.Gram, sugar)
        )));

        recipeRepository.save(new Recipe("borscht", "mmmm", Arrays.asList(
                new Component(500, Component.Unit.Gram, flour),
                new Component(10, Component.Unit.Liter, water),
                new Component(5, Component.Unit.Gram, sugar)
        )));
    }
}
