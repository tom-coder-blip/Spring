package tacos.tacos03.data;

// This interface defines how TacoOrder objects are saved and retrieved.
// It abstracts the data layer so the rest of the app doesn't care about the database details.
// Implementations will handle the actual DB logic.

// Optional lets us return a value that might be missing
import java.util.Optional;

import tacos.tacos03.TacoOrder;

// Repository interface for saving and loading TacoOrder data
public interface OrderRepository {

    // Save a new order or update an existing one
    TacoOrder save(TacoOrder order);

    // Find an order by its ID, may return empty if not found
    Optional<TacoOrder> findById(Long id);

}
