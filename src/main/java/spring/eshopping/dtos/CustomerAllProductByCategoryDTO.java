package spring.eshopping.dtos;


import spring.eshopping.entities.product.Product;

import java.util.List;

public class CustomerAllProductByCategoryDTO {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
