package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;
import com.product.api.dto.ApiResponse;
import com.product.api.entity.Product;
import com.product.api.repository.RepoCategory;
import com.product.api.repository.RepoProduct;
import com.product.exception.ApiException;

@Service
public class SvcProductImp implements SvcProduct {

	@Autowired
	RepoProduct repo;

	@Autowired
	RepoCategory repoCategory;

	@Override
	public Product getProduct(String gtin) {
		Product product = repo.findByGTIN(gtin); // sustituir null por la llamada al método implementado en el repositorio
		if (product != null) {
			product.setCategory(repoCategory.getCategory(product.getCategory_id()));
			return product;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
	}

	/*
	 * 4. Implementar el método createProduct considerando las siguientes validaciones:
  		1. validar que la categoría del nuevo producto exista
  		2. el código GTIN y el nombre del producto son únicos
  		3. si al intentar realizar un nuevo registro ya existe un producto con el mismo GTIN pero tiene estatus 0,
  		   entonces se debe cambiar el estatus del producto existente a 1 y actualizar sus datos con los del nuevo registro
	 */
	@Override
	public ApiResponse createProduct(Product in) {
		in.setStatus(1);
		try{
			repo.save(in);
		}/*catch(ConstraintViolationException e){
			Product pr =(Product) repo.findByGTIN(in.getGtin());
			if(e.getLocalizedMessage().contains("gtin") && pr.getStatus()==1){
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exists");
			}else{
				repo.activateProductByGTIN(pr.getGtin());
				return new ApiResponse("product activated");
			}
			if(e.getLocalizedMessage().contains("product") && pr.getStatus()==1){
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exists");
			}else{
				repo.activateProductByProductName(pr.getProduct());
				return new ApiResponse("product activated");
			}
		}*/catch(DataIntegrityViolationException e){
			Product pr =(Product) repo.findByGTINnotStatusMarked(in.getGtin());
			if(e.getLocalizedMessage().contains("gtin") && (pr==null || pr.getStatus()==1)){
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exists");
			}else if (e.getLocalizedMessage().contains("gtin") && pr.getStatus()==0){
				repo.activateProductByGTIN(pr.getGtin());
				repo.updateProduct(pr.getProduct_id(), pr.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(),in.getCategory_id());
				return new ApiResponse("product activated");
			}else if(e.getLocalizedMessage().contains("product") && (pr==null || pr.getStatus()==1)){
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exists");
			}else if (e.contains(SQLIntegrityConstraintViolationException.class))
						throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		return new ApiResponse("product created");
	}

/**
	public ApiResponse createCategory(Category category){
		Category cat=(Category) categoryRepository.findByCategory(category.getCategory());
		if(cat !=null){
			if(cat.getStatus()==0){
				categoryRepository.activateCategory(cat.getCategory_id());
				return new ApiResponse( "region has been activated");
			}else{
				throw new ApiException(HttpStatus.BAD_REQUEST,"region already exists");
			}
		}
		categoryRepository.createCategory(category.getCategory());
		return new ApiResponse("region created");
	}*/

	@Override
	public ApiResponse updateProduct(Product in, Integer id) {
		Integer updated = 0;
		try {
			updated = repo.updateProduct(id, in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
		}catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("gtin"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exist");
			if (e.getLocalizedMessage().contains("product"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exist");
			if (e.contains(SQLIntegrityConstraintViolationException.class))
				throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		if(updated == 0)
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be updated");
		else
			return new ApiResponse("product updated");
	}

	@Override
	public ApiResponse deleteProduct(Integer id) {
		if (repo.deleteProduct(id) > 0)
			return new ApiResponse("product removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be deleted");
	}

	@Override
	public ApiResponse updateProductStock(String gtin, Integer stock) {
		Product product = getProduct(gtin);
		if(stock > product.getStock())
			throw new ApiException(HttpStatus.BAD_REQUEST, "stock to update is invalid");

		repo.updateProductStock(gtin, product.getStock() - stock);
		return new ApiResponse("product stock updated");
	}
}
