package tacos.tacos05.data;

// Spring Data interface that gives CRUD operations automatically
import org.springframework.data.repository.CrudRepository;

import tacos.tacos05.Ingredient;

// Repository for Ingredient entities
// CRUD operations: save, findById, findAll, delete...
public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}