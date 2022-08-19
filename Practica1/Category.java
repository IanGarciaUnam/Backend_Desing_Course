import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;


class Category{
		private Integer category_id;
		private String category;

		/**
		*Constructor de la clase Category
		*
		*@params
		* int category_id : ID de la categoría
		*	String category : nombre de la categoría
		*/
		Category(int category_id, String category){
			this.category_id=category_id;
			this.category=category;
		}

		/**
		*Devuelve el Id de la categoría
		*@returns
		 *category_id
		*/
		public Integer getCategory_id(){
			return this.category_id;
		}
		/*
		*Devuelve el nombre de la categoría
		*@returns
		*category
		*/
		public String getCategory(){
			return this.category;
		}


		public boolean equals(CategoryManager.Category c){
			return this.category_id==c.getCategory_id();
		}
		@Override
		public String toString(){
			return "Category_id: "+category_id.toString()+" Category: "+ category;
	}
}
