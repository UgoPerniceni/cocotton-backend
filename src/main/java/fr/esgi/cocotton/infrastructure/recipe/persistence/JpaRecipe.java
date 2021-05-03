package fr.esgi.cocotton.infrastructure.recipe.persistence;

import fr.esgi.cocotton.infrastructure.profile.persistance.JpaProfile;
import fr.esgi.cocotton.infrastructure.ingredient.persistence.JpaIngredient;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name = "recipe")
public class JpaRecipe {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    private List<String> steps;

    @ManyToOne
    private JpaProfile user;

    @ManyToMany
    List<JpaIngredient> ingredients;

    public JpaRecipe(){}

    public JpaRecipe(String id, String name, JpaProfile user) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.steps = new ArrayList<>();
        this.ingredients = new ArrayList<>();
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

    public JpaProfile getProfile() {
        return user;
    }

    public void setProfile(JpaProfile user) {
        this.user = user;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<JpaIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<JpaIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
