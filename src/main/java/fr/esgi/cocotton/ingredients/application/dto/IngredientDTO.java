package fr.esgi.cocotton.ingredients.application.dto;

import fr.esgi.cocotton.ingredients.domain.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IngredientDTO {

    @NotNull
    @Size(min = 1, max = 30)
    private final String name;

    @NotNull
    private final Category category;

    public IngredientDTO(String name, Category category){
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
