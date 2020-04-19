package spring.eshopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.eshopping.dtos.CategoryMetadataDTO;
import spring.eshopping.services.CategoryMetadataService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/categoryMetadata")
public class CategoryMetadataController {

    @Autowired
    private CategoryMetadataService categoryMetadataService;

    @PostMapping("/add")
    public String addCategoryMetadata(@RequestBody CategoryMetadataDTO categoryMetadataDTO, HttpServletResponse response) {
        String getMessage = categoryMetadataService.addCategoryMetadata(categoryMetadataDTO);
        if ("Success".contentEquals(getMessage)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @PutMapping("/update")
    public String updateCategoryMetadata(@RequestBody CategoryMetadataDTO categoryMetadataDTO,HttpServletResponse response) {
        String getMessage = categoryMetadataService.updateCategory(categoryMetadataDTO);
        if ("Success".contentEquals(getMessage)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

}
