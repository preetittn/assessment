package spring.eshopping.dtos;

import spring.eshopping.entities.category.Category;

import java.util.HashMap;
import java.util.Set;

public class CategoryDTO{
    private Category category;//till root
    private Set<HashMap<String,String>> filedValuesSet;//meta data (key,value)
    private Set<Category> childCategory;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<HashMap<String, String>> getFiledValuesSet() {
        return filedValuesSet;
    }

    public void setFiledValuesSet(Set<HashMap<String, String>> filedValuesSet) {
        this.filedValuesSet = filedValuesSet;
    }

    public Set<Category> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(Set<Category> childCategory) {
        this.childCategory = childCategory;
    }
}