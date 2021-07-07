package fr.esgi.cocotton.recipe.infrastructure.controller;

import fr.esgi.cocotton.recipe.application.AddRecipe;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {
    @InjectMocks
    RecipeController recipeController;
    @Mock
    AddRecipe addRecipe;
}
