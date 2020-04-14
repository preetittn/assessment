package spring.eshopping.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.Address;

public interface AddressRepo extends CrudRepository<Address,Long> {
}
