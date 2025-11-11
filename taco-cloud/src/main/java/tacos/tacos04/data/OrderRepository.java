package tacos.tacos04.data;

import org.springframework.data.repository.CrudRepository;
import tacos.tacos04.TacoOrder;

// MongoDB repository interface for TacoOrder documents.
// Spring Data auto-creates all CRUD methods at runtime.
// No implementation class needed, Spring wires everything for you.
public interface OrderRepository
        extends CrudRepository<TacoOrder, String> {

}
