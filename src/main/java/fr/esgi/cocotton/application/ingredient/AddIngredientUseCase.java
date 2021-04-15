package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.enums.category.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface AddIngredientUseCase {

    String execute(AddIngredientCommand command);

    class AddIngredientCommand {

        @NotNull
        @Size(min = 1, max = 30)
        private final String name;

        @NotNull
        private final Category category;

        public AddIngredientCommand(String name, Category category){
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
}
