package com.product.api.controller;
import com.product.api.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.LinkedList;
import java.util.HashMap;
import com.product.api.entity.CategoryManager;
/**
*Clase RestController para el control del envío respuesta
* a request relacionados con categoría
*/
@RestController
public class CtrlCategory{
	private  static LinkedList<HashMap<String, Object>> api_list=new LinkedList<>();
	private  static LinkedList<Category> categories_list= new LinkedList<>();


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
	public CtrlCategory(LinkedList<Category> categories){
		this.categories_list=categories;
		fromCatToAPI();
	}

	public  void setCategoriesList(LinkedList<Category> categories){
		this.categories_list=categories;
		fromCatToAPI();
	}

	public  void fromCatToAPI(){

		for(Category c: categories_list){
			//System.out.println(c.toString());
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
		System.out.println("==categories list==");
		for(Category c: categories_list){
			System.out.println(c.toString());
		}
		return api_list;
		//return new String[]{"categoría", "pollos", "verduras"};
	}
	@GetMapping("/category/{id}")
	@ResponseBody
	public LinkedList<HashMap<String, Object>> getCategoryById(@PathVariable Integer id){
		boolean flag=false;
		LinkedList<HashMap<String, Object>> local= new LinkedList<>();
		for(Category c: categories_list){
			if(c.getCategory_id().equals(id)){
				flag=!flag;
				local.add(new HashMap<String, Object>(){
					{
						put("category_id", c.getCategory_id());
						put("category", c.getCategory());

					}
				});
			}
			if(flag){
				return local;
			}

		}

		local.add(new HashMap<String, Object>(){
			{
				put("message", "Category doesn't exists");
			}
		});
		return local;
	}

}
