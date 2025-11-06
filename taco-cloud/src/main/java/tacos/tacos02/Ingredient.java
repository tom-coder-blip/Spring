package tacos.tacos02; // Package: groups related classes together

//This class helps your app organize ingredients.
//When someone designs a taco, the app knows: “This is a cheese item” or “This is a wrap.”

// Lombok annotation provider to generate boilerplate (getters, toString, equals, etc.)
import lombok.Data;

@Data // Generates getters, toString(), equals(), hashCode() and a constructor for final fields
public class Ingredient {

    private final String id;   // Unique ID for the ingredient (e.g. "FLTO")
    private final String name; // Human-readable name (e.g. "Flour Tortilla")
    private final Type type;   // Category of ingredient (wrap, protein, etc.)

    // Enum listing the allowed ingredient categories used by the app
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
