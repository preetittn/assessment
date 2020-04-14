package spring.eshopping.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.Customer;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer,Long> {
    List<Customer> findAll(Pageable pageable);
    Customer findByEmail(String email);

}
