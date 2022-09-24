package com.product.api.service;
import org.springframework.boot.SpringApplication;
import java.util.List;
import com.product.api.entity.Category;

public interface SVCCategory{


    List<Category> getCategories();
    Category getCategory(Integer id);
    String createCategory(Category category);
    String updateCategory(Integer id, Category category);
    String deleteCategory(Integer id);


}
