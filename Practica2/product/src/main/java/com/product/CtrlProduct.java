package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.LinkedList;
import java.util.HashMap;
import com.product.CategoryManager.Category;

/**
*Clase RestController para el control del envío respuesta
* a request relacionados con categoría
*/
@RestController
public class CtrlProduct{
	private static LinkedList<HashMap<String, Object>> api_list=new LinkedList<>();


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
	public static void fromCatToAPI(LinkedList<Category> l_cat){

		for(Category c: l_cat){
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
		return api_list;
		//return new String[]{"categoría", "pollos", "verduras"};
	}

}
