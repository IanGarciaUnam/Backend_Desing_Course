package com.product.api.service;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.LinkedList;
import org.springframework.http.HttpStatus;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.repository.RepoCategory;
import com.product.api.repository.RepoProduct;
import com.product.api.service.SVCCategory;
import org.springframework.beans.factory.annotation.Autowired;
import com.product.api.dto.ApiResponse;
import com.product.api.dto.DTOProductCategory;
import com.product.exception.ApiException;
@Service
public class SVCCategoryImp implements SVCCategory {

    @Autowired
    RepoCategory categoryRepository;

    @Autowired
    RepoProduct productRepository;

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
      Category cat2=(Category) categoryRepository.findByCategory(category.getCategory());
      if(cat2 !=null){
        if(cat2.getStatus()==0){
            categoryRepository.activateCategory(cat2.getCategory_id());
            categoryRepository.updateCategory(cat2.getCategory_id(),cat2.getCategory());
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
      Category cat=(Category)categoryRepository. findByCategoryIdNotStatusMarked(id);
      if(cat==null){
        throw new ApiException(HttpStatus.NOT_FOUND, "category does not exists");
      } else if(cat.getStatus()==0){
        throw new ApiException(HttpStatus.NOT_FOUND, "category is not active");
        //categoryRepository.activateCategory(cat.getCategory_id());
        //categoryRepository.updateCategory(id, category.getCategory());
        //return new ApiResponse( "category has been activated");
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
      Category c=(Category) categoryRepository.findByCategoryId(id);
      if(c==null)
        throw new ApiException(HttpStatus.NOT_FOUND,"category does not exists");

      List<Product> products= productRepository.getListProducts(id);
      List<DTOProductCategory> dtoProductCategory= new LinkedList<>();
      for(Product p: products){
          dtoProductCategory.add(becomeProductToDTO(p));
      }
      return dtoProductCategory;
    }

    private DTOProductCategory becomeProductToDTO(Product p){
      return new DTOProductCategory(p.getProduct_id(), p.getGtin(), p.getProduct(), p.getPrice());
    }


}
