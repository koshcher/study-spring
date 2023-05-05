package ua.step.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.step.recipes.dtos.ComponentDto;
import ua.step.recipes.dtos.SearchDto;
import ua.step.recipes.models.Component;
import ua.step.recipes.models.Product;
import ua.step.recipes.models.Recipe;
import ua.step.recipes.repositories.ComponentRepository;
import ua.step.recipes.repositories.ProductRepository;
import ua.step.recipes.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping({"/","/recipes"} )
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String getAll(
        @PageableDefault(size = 5) Pageable pageable, Model model, SearchDto searchDto
    ) {
        Page<Recipe> recipes;
        if(searchDto.getProducts().isEmpty()) {
            recipes = recipeRepository.findAllByNameStartsWith(searchDto.getRecipeName(), pageable);
        } else {
            recipes = recipeRepository.findAllByProductsAndName(searchDto.getProducts(), searchDto.getRecipeName(), pageable);
        }

        model.addAttribute("recipes", recipes);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("search", searchDto);
        return "recipes";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String getNew() {return "new"; }

    @PostMapping(value = "/new")
    public String postNew( Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/"+recipe.getId();
    }

    @GetMapping("/{id}")
    public String getRecipe(Model model, @PathVariable Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.getRecipeById(id);
        if(optionalRecipe.isEmpty()) return "redirect:/";
        Recipe recipe = optionalRecipe.get();
        model.addAttribute("recipe", recipe);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("units", Arrays.asList(Component.Unit.values()));
        return "recipe";
    }

    @PostMapping("/component")
    public String postComponent( ComponentDto componentDto) {
        Optional<Recipe> optionalRecipe = recipeRepository.getRecipeById(componentDto.getRecipeId());
        Optional<Product> optionalProduct = productRepository.getProductsById(componentDto.getProductId());
        if(optionalRecipe.isEmpty() || optionalProduct.isEmpty()) return "redirect:/";
        Recipe recipe = optionalRecipe.get();
        Product product = optionalProduct.get();

        Component component = new Component(componentDto.getAmount(), componentDto.getUnit(), product, recipe);
        componentRepository.save(component);
        return "redirect:/"+recipe.getId();
    }

}
