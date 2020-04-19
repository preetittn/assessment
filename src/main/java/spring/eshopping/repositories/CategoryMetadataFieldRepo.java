package spring.eshopping.repositories;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import spring.eshopping.entities.category.CategoryMetadataField;

import java.util.List;

public interface CategoryMetadataFieldRepo extends CrudRepository<CategoryMetadataField,Long> {
    CategoryMetadataField findByName(String name);
    List<CategoryMetadataField> findAll(Pageable pageable);

}
