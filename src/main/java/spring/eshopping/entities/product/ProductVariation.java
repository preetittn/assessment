package spring.eshopping.entities.product;

import spring.eshopping.entities.utils.HashMapConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;

@Entity
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long price;
    private Long quantityAvailable;
    private Boolean isActive;

    @Convert(converter = HashMapConverter.class)
    private Map<String, HashSet<String>> metadata;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Map<String, HashSet<String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, HashSet<String>> metadata) {
        this.metadata = metadata;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
