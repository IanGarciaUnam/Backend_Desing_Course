package com.product;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collection;


/**
*Clase CategoryManager, su objetivo es manejar a la clase Categoría
*
*
*/
public class CategoryManager{

  private static LinkedList<Category> categories_list=new LinkedList<>();
  /*  CategoryManager(){
      categories_list= new LinkedList<>();
    }*/
		/**
		*Clase Category
		*Modela una Categoría a traves de los
		*parámetros  dados, category_id y category; id de la categoría y nombre de las categorías
		*respectivamente
		*/


	class Category{
			/*Decidí que el id debería ser un número entero sin límites de tamaño*/
			private Integer category_id;
			/*La categoría quedará definida como un String, ya que permitirá ser más extensa e incluso explicativa*/
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




	/**
	*El método crea una categoría y la añade a la lista de categorías
	*@params
	*category_id : Id de las categoría
	*category : Nombre de la categoría
	*/
	public  static void createCategory(Integer category_id, String category){
		//System.out.println(getObject(category_id).toString());
		CategoryManager cm= new CategoryManager();
		Category c=cm.new Category(category_id, category);
		if(categories_list.contains(c)){
				System.out.printf("Una categoría con el id %d ya se encuentra previamente registrada\n",c.getCategory_id());
				return;
			}else{
				categories_list.add(c);//revisar
				System.out.println(c.toString());
			}

	}

	/**
	*Devuelve una lista con la lista de las categorias registradas
	*
	*/
	public  static LinkedList<Category> getCategories(){
    return categories_list;
	}

	/**
	*Devuelve la categoría correspondiente al ID
	*imprimiendola en pantalla
	*/
	public  Category getCategory(Integer category_id){
    for(Category c: categories_list){
        if(c.getCategory_id().equals(category_id))
          return c;
    }
    return null;
	}

	private static <T> T isMyObjectThere(T t2, Collection<T> list){
		if(!list.contains(t2)) return null;
		for(T t : list){
			if(t2.equals(t))
				return t;
		}
		return null;
	}
	/**
	*Borra una categoría indicada por el ID
	*
	*/
	public  void deleteCategory(Integer category_id){
		Category fakeCategory=this.new Category(category_id);
		if(categories_list.contains(fakeCategory)){
				categories_list.remove(isMyObjectThere(fakeCategory, categories_list));
				System.out.println("Categoría removida con éxito");
				return;
		}
		System.out.println("No existe una categoría con el id ingresado");
	}
}
