package spring.eshopping.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.UserLoginFailCounter;

import java.util.Optional;

public interface UserLoginFailCounterRepo extends CrudRepository<UserLoginFailCounter,Long> {
    Optional<UserLoginFailCounter> findByEmail(String email);
}
