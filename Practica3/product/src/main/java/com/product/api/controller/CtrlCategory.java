package com.product.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.LinkedList;
import java.util.HashMap;
import com.product.api.entity.CategoryManager.Category;

/**
*Clase RestController para el control del envío respuesta
* a request relacionados con categoría
*/
@RestController
public class CtrlCategory{
	private  LinkedList<HashMap<String, Object>> api_list=new LinkedList<>();
	private  LinkedList<Category> categories_list= new LinkedList<>();


	/**
	*Convierte una lista con el contenido de Categorías a uno transferible a
	*la API de formato
	* [{
	* category_id: int,
	* category: String
	* }...
	* ]
	*
	*/
	CtrlCategory(LinkedList<Category> categories){
		this.categories_list=categories;
		fromCatToAPI();
	}

	public  void setCategoriesList(LinkedList<Category> categories){
		this.categories_list=categories;
		fromCatToAPI();
	}

	public  void fromCatToAPI(){

		for(Category c: categories_list){
			api_list.add(new HashMap<String, Object>() {
				{
					put("category_id", c.getCategory_id());
					put("category", c.getCategory());
				}
			});
		}
	}
	/**
	*Devuelve una lista con el contenido de la API
	*
	*/
	@GetMapping("/category")
	public LinkedList<HashMap<String, Object>> getCategory(){
		this.fromCatToAPI();
		return api_list;
		//return new String[]{"categoría", "pollos", "verduras"};
	}
	@GetMapping("/category/{id}")
	@ResponseBody
	public LinkedList<HashMap<String, Object>> getCategoryById(@PathVariable Integer id){
		LinkedList<HashMap<String, Object>> local= new LinkedList<>();
		for(Category c: categories_list){
			if(c.getCategory_id().equals(id)){
				local.add(new HashMap<String, Object>(){
					{
						put("category_id", c.getCategory_id());
						put("category", c.getCategory());

					}
				});
			}

					return local;
		}

		local.add(new HashMap<String, Object>(){
			{
				put("message", "Category doesn't exists");
			}
		});
		return local;
	}

}
