package com.product.api.service;
import org.springframework.boot.SpringApplication;
import java.util.List;
import com.product.api.entity.Category;
import com.product.api.dto.ApiResponse;
import com.product.apit.dto.DTOProductCategory;
public interface SVCCategory{


    List<Category> getCategories();
    Category getCategory(Integer id);
    ApiResponse createCategory(Category category);
    ApiResponse updateCategory(Integer id, Category category);
    ApiResponse deleteCategory(Integer id);
    DTOProductCategory getListProducts(Integer id);

}
