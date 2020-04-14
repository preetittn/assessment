package spring.eshopping.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.users.Seller;

import java.util.List;

public interface SellerRepo extends CrudRepository<Seller,Long> {
    List<Seller> findByGst(String gst);
    Seller findByCompanyName(String companyName);
    List<Seller> findAll(Pageable pageable);
    Seller findByEmail(String email);
}
