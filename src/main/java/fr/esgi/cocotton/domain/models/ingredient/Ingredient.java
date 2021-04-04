package fr.esgi.cocotton.domain.models.ingredient;

import fr.esgi.cocotton.domain.enums.category.Category;

public class Ingredient {

    private String name;
    private Category category;

    public Ingredient(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
