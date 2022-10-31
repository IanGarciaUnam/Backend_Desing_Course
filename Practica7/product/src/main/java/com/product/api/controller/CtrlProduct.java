package com.product.api.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import com.product.api.dto.ApiResponse;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.api.service.SVCCategory;
import com.product.exception.ApiException;
import com.product.api.dto.DTOCategory;
import com.product.api.dto.DTOProductCategory;
@RestController
@RequestMapping("/product")
public class CtrlProduct {

	@Autowired
	SvcProduct svc;
	@Autowired
	SVCCategory svcCategory;

	// 1. Implementar método getProduct

	/**
	*Devuelve una lista con el contenido de la API
	*
	*/
	@GetMapping
	public ResponseEntity<String> getProduct(){
		return new ResponseEntity<>("Action not available for products", HttpStatus.NOT_FOUND);
	}

	/**
	*Devuelve la categoría con el id correspondiente
	*en caso de que no exista, un mensaje indicandolo
	*/
	@GetMapping("/{gtin}")
	@ResponseBody
	public ResponseEntity<Product> getProductByGTIN(@PathVariable String gtin){
		return new ResponseEntity<>(svc.getProduct(gtin), HttpStatus.OK);
	}

	@GetMapping("category/{id}")
	@ResponseBody
	public ResponseEntity<List<DTOProductCategory>> getListProducts(@PathVariable Integer id){
		return new ResponseEntity<>(svcCategory.getListProducts(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createProduct(in),HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateProduct(in, id),HttpStatus.OK);
	}

	// 2. Implementar método updateProductStock

	@PutMapping("/{gtin}/stock/{stock}")
	public ResponseEntity<ApiResponse> updateProductStock(@PathVariable("gtin") String gtin, @PathVariable("stock") Integer stock){
		//if(bindingResult.hasErrors())
			//throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateProductStock(gtin, stock),HttpStatus.OK);
	}

	@PutMapping("/{gtin}/category")
	public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable("gtin") String gtin,  @RequestBody(required = true) @Valid DTOCategory dtoCategory, BindingResult bindingResult){
		System.out.println("hola uwu");
		//if(bindingResult.hasErrors())
			//throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		System.out.println(dtoCategory.getCategory_id());
		return new ResponseEntity<>(svc.updateProductCategory(gtin, dtoCategory), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteProduct(id), HttpStatus.OK);
	}
}
