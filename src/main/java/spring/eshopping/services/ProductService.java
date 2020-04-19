package spring.eshopping.services;


import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.eshopping.dtos.CustomerAllProductByCategoryDTO;
import spring.eshopping.dtos.CustomerProductViewByIdDTO;
import spring.eshopping.dtos.ProductDTO;
import spring.eshopping.entities.category.Category;
import spring.eshopping.entities.product.Product;
import spring.eshopping.entities.users.Seller;
import spring.eshopping.exception.FieldAlreadyExistException;
import spring.eshopping.exception.ResourceNotFoundException;
import spring.eshopping.repositories.CategoryRepo;
import spring.eshopping.repositories.ProductRepo;
import spring.eshopping.repositories.SellerRepo;
import spring.eshopping.utils.SendEmail;
import spring.eshopping.utils.UserEmailFromToken;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    UserEmailFromToken userEmailFromToken;
    @Autowired
    SellerRepo sellerRepo;
    @Autowired
    SendEmail sendEmail;
    @Autowired
    ModelMapper modelMapper;


    public String addProduct(HttpServletRequest request, String name, String brand, Long categoryId, Optional<String> desc, Optional<Boolean> isCancellable, Optional<Boolean> isReturnable) {
        // check for leaf category
        List<Object> parentIds = categoryRepo.findLeafCategories();
        List<Object> leafCategoryIds = categoryRepo.findCategoryId();
        leafCategoryIds.removeAll(parentIds);
        Set<Long> leafs = new HashSet<>();
        leafCategoryIds.forEach(i->{
            leafs.add(Long.parseLong(i.toString()));
        });
        if (!(leafs.contains(categoryId))) {
            throw new ResourceNotFoundException(categoryId +" not a leaf category");
        }
        String sellerEmail = userEmailFromToken.getUserEmail(request);
        Seller seller = sellerRepo.findByEmail(sellerEmail);
        Optional<Product> checkUniqueName = productRepo.checkUniqueProductName(brand,name,seller.getId(),categoryId);
        if (checkUniqueName.isPresent()) {
            throw new FieldAlreadyExistException(name + " product already exist");
        }
        Product product = new Product();
        Optional<Category> category = categoryRepo.findById(categoryId);
        product.setName(name);
        product.setBrand(brand);
        product.setActive(false);
        product.setDeleted(false);
        product.setCategory(category.get());
        product.setSeller(seller);
        if (desc.isPresent()) {
            product.setDescription(desc.get());
        }
        if (isCancellable.isPresent()) {
            product.setCancellable(isCancellable.get());
        }
        if (!isCancellable.isPresent()) {
            product.setCancellable(true);
        }
        if (isReturnable.isPresent()) {
            product.setReturnable(isReturnable.get());
        }
        if (!isReturnable.isPresent()) {
            product.setReturnable(true);
        }
        seller.getProducts().add(product);
        category.get().getProducts().add(product);

        productRepo.save(product);
        categoryRepo.save(category.get());
        sellerRepo.save(seller);
        sendEmail.sendEmail("ACTIVATE ADDED PRODUCT",name+" " +categoryId+" "+brand,"preeti@mail.com");

        return "Success";

    }

    public ProductDTO viewProduct(Long id, HttpServletRequest request) {
        String userEmail = userEmailFromToken.getUserEmail(request);
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(id+" product not found");
        }
        Seller seller = sellerRepo.findByEmail(userEmail);
        if (product.get().getSeller().getId() != seller.getId()) {
            throw new  ResourceNotFoundException("invalid seller");
        }
        if (product.get().getDeleted()) {
            throw new ResourceNotFoundException(id+" product is deleted");
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setActive(product.get().getActive());
        productDTO.setBrand(product.get().getBrand());
        productDTO.setCancellable(product.get().getCancellable());
        productDTO.setCategory(product.get().getCategory());
        productDTO.setDescription(product.get().getDescription());
        productDTO.setName(product.get().getName());
        productDTO.setReturnable(product.get().getReturnable());
        productDTO.setSeller(product.get().getSeller().getId());
        productDTO.setId(id);

        return productDTO;
    }

    public List<?> viewAllProducts(HttpServletRequest request, String page, String size, String sortBy, String order, Optional<String> query) {
        if (query.isPresent()) {
            ProductDTO productDTO =viewProduct(Long.parseLong(query.get()),request);
            List<ProductDTO> productDTOS = new ArrayList<>();
            productDTOS.add(productDTO);
            return productDTOS;
        }
        String sellerEmail = userEmailFromToken.getUserEmail(request);
        Seller seller = sellerRepo.findByEmail(sellerEmail);
        List<Product> products = productRepo.productsOfSeller(seller.getId(), PageRequest.of(Integer.parseInt(page),Integer.parseInt(size), Sort.by(Sort.Direction.fromString(order),sortBy)));
        return products;
    }

    public String deleteProductById(Long id, HttpServletRequest request) {
        String userEmail = userEmailFromToken.getUserEmail(request);
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(id+" product not found");
        }
        Seller seller = sellerRepo.findByEmail(userEmail);
        if (product.get().getSeller().getId() != seller.getId()) {
            throw new  ResourceNotFoundException("invalid seller");
        }
        product.get().setDeleted(true);
        productRepo.save(product.get());
        return "Success";
    }

    public String updateProductById(HttpServletRequest request, Long id, Optional<String> name, Optional<String> desc, Optional<Boolean> isCancellable, Optional<Boolean> isReturnable) {
        String userEmail = userEmailFromToken.getUserEmail(request);
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(id+" product not found");
        }
        Seller seller = sellerRepo.findByEmail(userEmail);
        if (product.get().getSeller().getId() != seller.getId()) {
            throw new  ResourceNotFoundException("invalid seller");
        }
        System.out.println(product.get().getBrand()+name.get()+seller.getId()+product.get().getCategory());

        //to check whether product is unique or not
        if (name.isPresent()) {
            Optional<Product> checkUniqueName = productRepo.checkUniqueProductName(product.get().getBrand(),name.get(),seller.getId(),product.get().getCategory().getId());
            if (checkUniqueName.isPresent()) {
                throw new FieldAlreadyExistException(name.get() + " product already exist");
            }
            product.get().setName(name.get());
        }
        if (desc.isPresent()) {
            product.get().setDescription(desc.get());
        }
        if (isCancellable.isPresent()) {
            product.get().setCancellable(isCancellable.get());
        }
        if (isReturnable.isPresent()) {
            product.get().setReturnable(isReturnable.get());
        }
        productRepo.save(product.get());
        return "Success";
    }

    public String activateProduct(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (!product.isPresent()) {
            throw new ResourceNotFoundException(productId+" not found");
        }
        if (product.get().getActive()) {
            throw new ResourceNotFoundException(productId+" already active");
        }
        product.get().setActive(true);
        sendEmail.sendEmail("PRODUCT ACTIVATED",productId+" product activated",product.get().getSeller().getEmail());
        productRepo.save(product.get());
        return "Success";
    }

    public String deactivateProduct(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
       if (!product.isPresent()) {
            throw new ResourceNotFoundException(productId+" not found");
        }
        if (!product.get().getActive()) {
            throw new ResourceNotFoundException(productId+" already de-active");
        }
        product.get().setActive(false);
        sendEmail.sendEmail("PRODUCT DE-ACTIVATED",productId+" product deactivated",product.get().getSeller().getEmail());
        productRepo.save(product.get());
        return "Success";
    }
}
