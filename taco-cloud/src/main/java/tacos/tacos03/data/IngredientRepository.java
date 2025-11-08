package tacos.tacos03.data;

// This interface defines how Ingredient objects are saved and retrieved.
// It abstracts the data layer so the rest of the app doesn’t rely on SQL directly.
// Implementations will handle the actual database logic.

// Optional is used when an ingredient might not be found
import java.util.Optional;

import tacos.tacos03.Ingredient;

// Defines basic CRUD operations for Ingredient data
public interface IngredientRepository {

    // Return all ingredients in the database
    Iterable<Ingredient> findAll();

    // Find a single ingredient by its ID (could be empty if not found)
    Optional<Ingredient> findById(String id);

    // Save a new ingredient or update an existing one
    Ingredient save(Ingredient ingredient);

}
