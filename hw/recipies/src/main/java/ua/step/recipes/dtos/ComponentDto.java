package ua.step.recipes.dtos;

import lombok.Data;
import ua.step.recipes.models.Component;

@Data
public class ComponentDto {
    private Long recipeId;

    private Long productId;

    private int amount;

    private Component.Unit unit;
}
