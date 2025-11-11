package tacos.tacos04;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data   // Lombok: auto-generates getters, setters, toString, etc.
public class Taco {

    @NotNull                                     // Name cannot be empty
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;                          // User's chosen taco name

    private Date createdAt = new Date();          // Timestamp when the taco is created

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();
    // List of ingredients chosen for this taco

    // Helper method to add a single ingredient
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}