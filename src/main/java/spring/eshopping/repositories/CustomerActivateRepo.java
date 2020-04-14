package spring.eshopping.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.CustomerActivate;

public interface CustomerActivateRepo extends CrudRepository<CustomerActivate,Long> {
    CustomerActivate findByUserEmail(String email);
    CustomerActivate findByToken(String token);
    void deleteByUserEmail(String email);

}
