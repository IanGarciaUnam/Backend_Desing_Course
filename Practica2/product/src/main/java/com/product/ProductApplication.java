package com.product;

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
		cm.createCategory(1, "verduras");
		cm.createCategory(2, "carnes");
		cm.createCategory(3, "ferretería");
		cm.createCategory(4, "autos");
		cm.createCategory(5, "farmacia");
		CtrlCategory.fromCatToAPI(cm.getCategories());
	}

}
