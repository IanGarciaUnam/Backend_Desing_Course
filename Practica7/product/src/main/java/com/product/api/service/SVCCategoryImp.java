package com.product.api.service;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.api.service.SVCCategory;
import org.springframework.beans.factory.annotation.Autowired;
import com.product.api.dto.ApiResponse;
import com.product.api.dto.DTOProductCategory;
import com.product.exception.ApiException;
@Service
public class SVCCategoryImp implements SVCCategory {

    @Autowired
    RepoCategory categoryRepository;

    @Override
    public List<Category> getCategories(){
      return categoryRepository.findByStatus(1);
    }

    @Override
    public Category getCategory(Integer id){
      Category c = categoryRepository.findByCategoryId(id);
      if(c==null){
        throw new ApiException(HttpStatus.NOT_FOUND, "category does not exists");
      }
      return c;
    }

    @Override
    public ApiResponse createCategory(Category category){
      Category cat=(Category) categoryRepository.findByCategory(category.getCategory());
      if(cat !=null){
        if(cat.getStatus()==0){
          categoryRepository.activateCategory(cat.getCategory_id());
          return new ApiResponse( "category has been activated");
        }else{
          throw new ApiException(HttpStatus.BAD_REQUEST,"category already exists");
        }
      }
      categoryRepository.createCategory(category.getCategory());
      return new ApiResponse("category created");
    }

    @Override
    public ApiResponse updateCategory(Integer id, Category category){
      Category cat=(Category)categoryRepository.findByCategoryId(id);
      if(cat==null){
        throw new ApiException(HttpStatus.NOT_FOUND, "category does not exists");
      } else if(cat.getStatus()==0){
        throw new ApiException(HttpStatus.BAD_REQUEST,"category is not active");
      }

       Category ext=(Category)categoryRepository.findByCategory(category.getCategory());
       if(ext==null){
         categoryRepository.updateCategory(id, category.getCategory());
       }else{
         throw new ApiException(HttpStatus.BAD_REQUEST,"category already exists");
       }
        return new ApiResponse("category updated");
      }


    @Override
    public ApiResponse deleteCategory(Integer id){
      Category cat=categoryRepository.findByCategoryId(id);
      if (cat==null){
        throw new ApiException(HttpStatus.NOT_FOUND,"category does not exists");
      }
      categoryRepository.deleteCategoryById(id);
      return new ApiResponse("category removed");
    }

    @Override
    public List<DTOProductCategory> getListProducts(Integer id){
      List<Category> cat= categoryRepository.getListProducts(id);
      List<DTOProductCategory> dtoProductCategory= new LinkedList<>();
      for(Category c: cat){
          dtoProductCategory.add(becomeCatInDTOCat(c));
      }
      return dtoProductCategory
    }

    private DTOProductCategory becomeCatInDTOCat(Category c){
      return new DTOProductCategory(c.getProduct_id(), c.getGTIN(), c.getProduct(), c.getPrice());
    }


}
