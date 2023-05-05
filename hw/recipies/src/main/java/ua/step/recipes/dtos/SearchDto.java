package ua.step.recipes.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchDto {
    private String recipeName = "";

    // products ids
    private List<Long> products = new ArrayList<>();
}
