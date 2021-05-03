package fr.esgi.cocotton.domain.models.recipe;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.domain.models.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String id;
    private String name;
    private List<Ingredient> ingredients;
    private Profile user;
    private List<String> steps;

    public Recipe(String id, String name, Profile user){
        this.id = id;
        this.name = name;
        this.steps = new ArrayList<>();
        this.ingredients = new ArrayList<>();
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Profile getProfile() {
        return user;
    }

    public void setProfile(Profile user) {
        this.user = user;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
