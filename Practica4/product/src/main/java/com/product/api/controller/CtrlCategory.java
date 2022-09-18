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
/**
*Clase RestController para el control del envío respuesta
* a request relacionados con categoría
*/

@RestController
@RequestMapping("/category")
public class CtrlCategory {
	private  static LinkedList<HashMap<String, Object>> api_list=new LinkedList<>();
	private  static LinkedList<Category> categories_list= new LinkedList<>();
	@Autowired
	private CategoryRepository categoryRepository;
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
		this.api_list.clear();
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
	@GetMapping
	public ResponseEntity<List<Category>> getCategory(){
		Category c1=new Category();
		c1.setCategoryId(1);
		c1.setCategory("Metales");
		c1.setStatus(1);
		Category c2=new Category();
		c2.setCategoryId(2);
		c2.setCategory("Metales2");
		c2.setStatus(1);

		List categories=new LinkedList();
		categories.add(c1);
		categories.add(c2);
		/*System.out.println("==categories list==");
		for(Category c: categories_list){
			System.out.println(c.toString());
		}
		return api_list;*/
		//return new String[]{"categoría", "pollos", "verduras"};
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	*Devuelve la categoría con el id correspondiente
	*en caso de que no exista, un mensaje indicandolo
	*/
	@GetMapping("{id}")
	@ResponseBody
	public ResponseEntity<List<Category>> getCategoryById(@PathVariable Integer id){
		/**boolean flag=false;
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


		}

		if(!flag)
			local.add(new HashMap<String, Object>(){
				{
					put("message", "Category does not exists");
				}
			});
		return local;*/
		Category c1=new Category();
		c1.setCategoryId(1);
		c1.setCategory("Metales");
		c1.setStatus(1);
		List categories=new LinkedList();
		categories.add(c1);
		//return new String[]{"categoría", "pollos", "verduras"};
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	/**
	*Crea una nueva categoría
	*
	*/
	@PostMapping("/category")
	public LinkedList<HashMap<String, Object>> createCategory( String category){
		/*	Collections.sort(categories_list);
			Category c=categories_list.getLast();
			Integer id=c.getCategory_id();
			id++;
			System.out.println(category);
			boolean found=false;
			if(this.categories_list.contains(new Category(id, category, 1))){
				found=!found;
			}
			LinkedList<HashMap<String, Object>> local= new LinkedList<>();
			String message; new String();
			if(!found){
				this.categories_list.add(new Category(id, category, 1));
				message= new String("Category created");
				fromCatToAPI();
			}else{
				message= new String("Category already exists");
			}

			local.add(new HashMap<String, Object>(){
				{
					put("message", message);
				}
			});
			System.out.println(categories_list);*/
			return null;

	}

	/**
	*Actualiza una categoría o en caso contrario señala su nula existencia
	*/
	@PutMapping("/category/{id}")
	public LinkedList<HashMap<String, Object>> updateCategoryById(@PathVariable Integer id, String category){
		//System.out.println(categories_list);
		/**boolean found=false;
		boolean aCategoryWasPreviouslyNamed=false;

		//System.out.println(category);

		for(Category c: categories_list){
			if(c.getCategory().equals(category)){
				aCategoryWasPreviouslyNamed=true;
			}
		}
		for(Category c:categories_list){
			if(c.getCategory_id().equals(id) && !aCategoryWasPreviouslyNamed){
					c.setCategory(category);
					found=!found;
			}
		}

		LinkedList<HashMap<String, Object>> local= new LinkedList<>();
		 String message;
		if(found && !aCategoryWasPreviouslyNamed){
			message= new String("Category updated");
		}else if(!found && !aCategoryWasPreviouslyNamed){
			message= new String("Category does not exists ");
		}else{
			message=new String("A category was previously named "+category);
		}
		fromCatToAPI();
		local.add(new HashMap<String, Object>(){
			{
				put("message", message);
			}
		});
		//System.out.println(categories_list);
		return local;*/
		return null;
	}

	/**
	*Borra una categoría o en caso de que no exista nos lo señala
	*
	*/
	@DeleteMapping("category/{id}")
	public LinkedList<HashMap<String, Object>> deleteCategoryById(@PathVariable Integer id){
		/**
		boolean found=false;
		Collections.sort(this.categories_list);
		Category signalizer=null;
		for(Category c: categories_list){
			if(c.getCategory_id().equals(id)){
					signalizer=c;
					found=!found;
				}
		}

		LinkedList<HashMap<String, Object>> local= new LinkedList<>();
		 String message;
		if(signalizer!=null){
			categories_list.remove(signalizer);
			message= new String("Category removed");
		}else{
			message= new String("Category does not exists");
		}
		fromCatToAPI();
		local.add(new HashMap<String, Object>(){
			{
				put("message", message);
			}
		});
		System.out.println(categories_list);
		return local;*/
		return null;
	}


}
