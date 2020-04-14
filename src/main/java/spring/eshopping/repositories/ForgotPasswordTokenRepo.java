package spring.eshopping.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.ForgotPasswordToken;

public interface ForgotPasswordTokenRepo extends CrudRepository<ForgotPasswordToken,Long> {
    ForgotPasswordToken findByUserEmail(String email);
    void deleteByUserEmail(String email);
}
