package com.product;
import com.product.api.controller.CtrlCategory;
import com.product.api.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.LinkedList;


/**
*Clase de ProductApplication
*inicializa el servicio
*
*/
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		/**CategoryManager cm = new CategoryManager();
		cm.createCategory(1, "verduras",1);
		cm.createCategory(2, "carnes",1);
		cm.createCategory(3, "ferretería",1);
		cm.createCategory(4, "autos",1);
		cm.createCategory(5, "farmacia",1);
	/*	LinkedList<Category> l= new LinkedList<>();
		Category c1= new Category(5, "farmacia",1);
		Category c2= new Category(4, "autos",1);
		Category c3= new Category(3, "ferretería",1);
		Category c4= new Category(2, "carnes",1);
		l.add(c1);
		l.add(c2);
		l.add(c3);
		l.add(c4);

		CtrlCategory cc= new CtrlCategory(cm.getCategories());//.fromCatToAPI(cm.getCategories());
		*/
	}

}
