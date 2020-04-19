package spring.eshopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.eshopping.entities.category.CategoryMetadataField;
import spring.eshopping.exception.FieldAlreadyExistException;
import spring.eshopping.repositories.CategoryMetadataFieldRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MetadataService {

    @Autowired
    CategoryMetadataFieldRepo metadataRepo;

    public String addMetadata(String fieldName) {
        if (metadataRepo.findByName(fieldName) != null) {
            throw new FieldAlreadyExistException(fieldName + " already exist");
        }
        CategoryMetadataField metadata = new CategoryMetadataField();
        metadata.setName(fieldName);
        metadataRepo.save(metadata);
        return "Success " + metadataRepo.findByName(fieldName).getId();
    }

    public List<CategoryMetadataField> viewMetadata(String page, String size, String sortBy, String order, Optional<String> query) {
        if (query.isPresent()) {
            List<CategoryMetadataField> categoryMetadataFields = new ArrayList<>();
            categoryMetadataFields.add(metadataRepo.findById(Long.parseLong(query.get())).get());
            return categoryMetadataFields;
        }
        List<CategoryMetadataField> categoryMetadataFields =  metadataRepo.findAll(PageRequest.of(Integer.parseInt(page),Integer.parseInt(size),Sort.by(Sort.Direction.fromString(order),sortBy)));
        return categoryMetadataFields;
    }
}
