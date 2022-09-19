package com.product.api.controller;
import org.springframework.boot.SpringApplication;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import com.product.api.entity.CategoryManager;
import com.product.api.entity.Category;
import com.product.api.repository.CategoryRepository;
import com.product.api.service.SVCCategory;
/**
*Clase RestController para el control del envío respuesta
* a request relacionados con categoría
*/

@RestController
@RequestMapping("/category")
public class CtrlCategory {
	//private  static LinkedList<HashMap<String, Object>> api_list=new LinkedList<>();
	//private  static LinkedList<Category> categories_list= new LinkedList<>();
	@Autowired
	private SVCCategory svc;

	/**
	*Devuelve una lista con el contenido de la API
	*
	*/
	@GetMapping
	public ResponseEntity<List<Category>> getCategory(){

		return new ResponseEntity<>(svc.getCategories(), HttpStatus.OK);
	}

	/**
	*Devuelve la categoría con el id correspondiente
	*en caso de que no exista, un mensaje indicandolo
	*/
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
		return new ResponseEntity<>(svc.getCategory(id), HttpStatus.OK);
	}

	/**
	*Crea una nueva categoría
	*
	*/
	@PostMapping
	public ResponseEntity<String> createCategory( @Valid @RequestBody Category c, BindingResult bindingResult){
		String message="";
		if(bindingResult.hasErrors()){
			message=bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(svc.createCategory(c), HttpStatus.OK);
	}

	/**
	*Actualiza una categoría o en caso contrario señala su nula existencia
	*/
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategoryById(@PathVariable Integer id, @Valid @RequestBody Category category,BindingResult bindingResult){
		String message="";
		if(bindingResult.hasErrors()){
			message=bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(svc.updateCategory(id, category), HttpStatus.OK);
	}

	/**
	*Borra una categoría o en caso de que no exista nos lo señala
	*
	*/
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Integer id){
		return new ResponseEntity<>(svc.deleteCategory(id), HttpStatus.OK);
	}


}
