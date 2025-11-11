package tacos.tacos05.data;

import org.springframework.data.repository.CrudRepository;
import tacos.tacos05.User;

// Repository for User entities
// Includes a custom finder: findByUsername()
public interface UserRepository extends CrudRepository<User, Long> {

    // Spring Data automatically generates SQL for this method
    User findByUsername(String username);
}
