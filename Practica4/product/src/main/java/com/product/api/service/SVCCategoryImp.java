package com.product.api.service;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import java.util.List;
import com.product.api.entity.Category;
import com.product.api.repository.CategoryRepository;
import com.product.api.service.SVCCategory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SVCCategoryImp implements SVCCategory {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories(){
      return categoryRepository.findByStatus(1);
    }

    @Override
    public Category getCategory(Integer id){
      Category c = categoryRepository.findByCategoryId(id);
      return c;
    }

    @Override
    public String createCategory(Category category){
      Category cat=(Category) categoryRepository.findByCategory(category.getCategory());
      if(cat !=null){
        if(cat.getStatus()==0){
          categoryRepository.activateCategory(cat.getCategory_id());
          return "category has been activated";
        }else{
          return "category already exists";
        }
      }
      categoryRepository.createCategory(category.getCategory());
      return "category created";
    }

    @Override
    public String updateCategory(Integer id, Category category){
      Category cat=(Category)categoryRepository.findByCategoryId(id);
      if(cat==null){
        return "category doesn't exist";
      } else if(cat.getStatus()==0){
        return "category is not active";
      }

       Category ext=(Category)categoryRepository.findByCategory(category.getCategory());
       if(ext==null){
         categoryRepository.updateCategory(id, category.getCategory());
       }else{
         return "category already exists";
       }

        return "category updated";
      }


    @Override
    public String deleteCategory(Integer id){
      Category cat=categoryRepository.findByCategoryId(id);
      if (cat==null){
        return "category doesn't exists";
      }
      categoryRepository.deleteCategoryById(id);
      return "category removed";
    }


}
