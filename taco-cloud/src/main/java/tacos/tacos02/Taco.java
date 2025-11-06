package tacos.tacos02;

//This class helps your app validate user input before saving or processing it.
//It ensures tacos are complete and meaningful—no empty names or ingredient-less tacos allowed!

import java.util.List; // Java List used to hold the taco's ingredients
import jakarta.validation.constraints.NotNull; // Validation: value must not be null
import jakarta.validation.constraints.Size; // Validation: size/length constraints
import lombok.Data; // Lombok: generates getters/setters/toString/equals/hashCode

@Data // Generates getters, setters, toString, equals, and hashCode
public class Taco {

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name; // Taco name (must be at least 5 chars)

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients; // Ingredients list (must contain >= 1 item)

}
