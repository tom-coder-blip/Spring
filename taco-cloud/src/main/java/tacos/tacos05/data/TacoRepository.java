package tacos.tacos05.data;

import org.springframework.data.repository.CrudRepository;

import tacos.tacos05.Taco;

// Repository for Taco entities
// Provides standard CRUD methods
public interface TacoRepository
        extends CrudRepository<Taco, Long> {
}
