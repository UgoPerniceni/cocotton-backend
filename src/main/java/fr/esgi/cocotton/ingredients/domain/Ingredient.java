package fr.esgi.cocotton.ingredients.domain;

public class Ingredient {

    private String name;
    private Category category;

    public Ingredient() {

    }

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
