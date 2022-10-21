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
	}

}
