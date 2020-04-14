package spring.eshopping.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByEmail(String Email);
    Optional<User> findById(Long id);
}
