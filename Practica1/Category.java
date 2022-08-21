import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;


	/**
	*Clase Category
	*Modela una Categoría a traves de los
	*parámetros  dados, category_id y category; id de la categoría y nombre de las categorías
	*respectivamente
	*/


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
		*Constructor para categoría cuyo único parámetro
		*es el id de la Categoría
		*@Params
		*category_id
		*/
		Category(int category_id){
			this.category_id=category_id;
			this.category="NA";
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

		@Override
		public boolean equals(Object c){
			if(c==this)return true;
			if(!(c instanceof Category))return false;
			Category cFromObject=(Category) c;
			return this.category_id.equals(cFromObject.getCategory_id());
		}
		@Override
		public String toString(){
			return this.category_id.toString()+", "+this.category;
	}
}
