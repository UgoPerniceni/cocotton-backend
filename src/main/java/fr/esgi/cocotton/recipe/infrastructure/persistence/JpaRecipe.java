package fr.esgi.cocotton.recipe.infrastructure.persistence;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Entity(name = "recipe")
public class JpaRecipe {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String title;

    private int people;

    @ElementCollection
    private List<String> steps;

    @ElementCollection
    private Set<String> ingredients;

    private String userId;

    public JpaRecipe(){}

    public JpaRecipe(String id, String title, int people, List<String> steps, Set<String> ingredients, String userId) {
        this.id = id;
        this.title = title;
        this.people = people;
        this.steps = steps;
        this.ingredients = ingredients;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
