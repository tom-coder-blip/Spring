package tacos.tacos02.web;

//It’s a Spring converter that takes a String ID (like "GRBF")
// and returns the matching Ingredient object (like "Ground Beef").

import java.util.HashMap; // Implementation of Map used for the lookup table
import java.util.Map;     // Map interface for mapping id -> Ingredient

import org.springframework.core.convert.converter.Converter; // Spring's Converter interface
import org.springframework.stereotype.Component;              // Marks this class as a Spring bean

import tacos.tacos02.Ingredient;     // Ingredient model class
import tacos.tacos02.Ingredient.Type; // Ingredient.Type enum for ingredient categories

@Component // Make this converter a Spring-managed bean so Spring can use it automatically
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    // Lookup map from ingredient ID (String) to Ingredient object
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    // Constructor: populate the map with known ingredients
    public IngredientByIdConverter() {
        ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
        ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }

    @Override
    // Convert a String id to an Ingredient by looking it up in the map
    public Ingredient convert(String id) {
        return ingredientMap.get(id); // returns null if id not found
    }

}
