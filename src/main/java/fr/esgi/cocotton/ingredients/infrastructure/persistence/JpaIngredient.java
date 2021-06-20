package fr.esgi.cocotton.ingredients.infrastructure.persistence;

import fr.esgi.cocotton.ingredients.domain.Category;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "ingredient")
public class JpaIngredient {

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

    @Enumerated(EnumType.STRING)
    private Category category;

    public JpaIngredient(){}

    public JpaIngredient(String name, Category category) {
        this.name = name;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
