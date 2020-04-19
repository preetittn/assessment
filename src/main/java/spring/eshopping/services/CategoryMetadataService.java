package spring.eshopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.eshopping.dtos.CategoryMetadataDTO;
import spring.eshopping.entities.category.Category;
import spring.eshopping.entities.category.CategoryMetadataField;
import spring.eshopping.entities.category.CategoryMetadataFieldValues;
import spring.eshopping.exception.ResourceNotFoundException;
import spring.eshopping.repositories.CategoryMetadataFieldRepo;
import spring.eshopping.repositories.CategoryMetadataFieldValuesRepo;
import spring.eshopping.repositories.CategoryRepo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryMetadataService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryMetadataFieldRepo metadataRepo;

    @Autowired
    CategoryMetadataFieldValuesRepo valuesRepo;

    public String addCategoryMetadata(CategoryMetadataDTO categoryMetadataDTO) {
        Optional<Category> category = categoryRepo.findById(categoryMetadataDTO.getCategoryId());
        if (!category.isPresent()) {
            throw new ResourceNotFoundException(categoryMetadataDTO.getCategoryId() + " category does not exist");
        }
        HashMap<String, HashSet<String>> filedIdValues = categoryMetadataDTO.getFiledIdValues();
        Set<String> metadataIds = filedIdValues.keySet();
        metadataIds.forEach(id->{
            Optional<CategoryMetadataField> metadata = metadataRepo.findById(Long.parseLong(id));
            if (!metadata.isPresent()) {
                throw new ResourceNotFoundException(id + " metadata filed does not exist");
            }
        });
        metadataIds.forEach(id->{
            if (filedIdValues.get(id).isEmpty()) {
                throw new ResourceNotFoundException("any one filed does not have values");
            }
        });
        CategoryMetadataFieldValues fieldValues = new CategoryMetadataFieldValues();
        fieldValues.setCategory(category.get());
        metadataIds.forEach(id->{
            Optional<CategoryMetadataField> metadata = metadataRepo.findById(Long.parseLong(id));
            fieldValues.setCategoryMetadataField(metadata.get());
            HashSet<String> values = filedIdValues.get(id);
            String value= String.join(",",values);
            fieldValues.setValue(value);
            metadata.get().getCategoryMetadataFieldValues().add(fieldValues);
            metadataRepo.save(metadata.get());
        });
        return "Success";
    }

    public String updateCategory(CategoryMetadataDTO categoryMetadataDTO) {
        Optional<Category> category = categoryRepo.findById(categoryMetadataDTO.getCategoryId());
        if (!category.isPresent()) {
            throw new ResourceNotFoundException(categoryMetadataDTO.getCategoryId() + " category does not exist");
        }
        HashMap<String, HashSet<String>> filedIdValues = categoryMetadataDTO.getFiledIdValues();
        Set<String> metadataIds = filedIdValues.keySet();
        metadataIds.forEach(id->{
            Optional<CategoryMetadataField> metadata = metadataRepo.findById(Long.parseLong(id));
            if (!metadata.isPresent()) {
                throw new ResourceNotFoundException(id + " metadata filed does not exist");
            }
            Optional<CategoryMetadataFieldValues> associationSet = valuesRepo.findByMetadataId(categoryMetadataDTO.getCategoryId(),Long.parseLong(id));
            if (!associationSet.isPresent()) {
                throw new ResourceNotFoundException("metadata filed is not associated with any category");
            }
            String value= String.join(",",categoryMetadataDTO.getFiledIdValues().get(id));
            associationSet.get().setValue(value);
            metadata.get().getCategoryMetadataFieldValues().add(associationSet.get());
            metadataRepo.save(metadata.get());
        });
        return "Success";
    }
}
