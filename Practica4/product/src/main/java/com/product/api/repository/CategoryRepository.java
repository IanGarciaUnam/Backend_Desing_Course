package com.product.api.repository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.product.api.entity.Category;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    @Query(value = "SELECT * from category where id = :category_id AND status = 1", nativeQuery=true)
    List<Category> findByCategoryId(@Param("category_id") Integer category_id);

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO  category(category, status) VALUES(:category,1)", nativeQuery=true)
    void createCategory(@Param("category") String Category);


    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET category = :category WHERE category_id = :category_id", nativeQuery = true)
    Integer updateRegion(@Param("category_id") Integer category_id, @Param("category") String category);


    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET status = 1 WHERE category_id = :category_id", nativeQuery = true)
    Integer activateCategory(@Param("category_id") Integer category_id);

    @Modifying
    @Transactional
    @Query(value="UPDATE category SET status = 0 WHERE category_id = :category_id", nativeQuery=true)
    void deleteCategoryById(@Param("category_id") Integer category_id);

}
