package com.product;
import com.product.api.controller.CtrlCategory;
import com.product.api.entity.CategoryManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
*Clase de ProductApplication
*inicializa el servicio
*
*/
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		CategoryManager cm = new CategoryManager();
		cm.createCategory(1, "verduras",1);
		cm.createCategory(2, "carnes",1);
		cm.createCategory(3, "ferretería",1);
		cm.createCategory(4, "autos",1);
		cm.createCategory(5, "farmacia",1);
		CtrlCategory cc= new CtrlCategory(cm.getCategories());//.fromCatToAPI(cm.getCategories());
	}

}
