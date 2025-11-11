package tacos.tacos05.data;

import org.springframework.data.repository.CrudRepository;

import tacos.tacos05.TacoOrder;

// Repository for TacoOrder entities
// Uses Long as primary key type
public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
}