package ua.step.recipes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RecipeDto {
    private Long id;

    @Schema(description = "Name of recipe")
    private String name;
    private String description;
    private String url;
    private LocalDateTime created;
    private LocalDateTime updated;
}
