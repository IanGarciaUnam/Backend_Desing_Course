package com.product.api.entity;
import com.product.api.entity.Category;
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

  public static LinkedList<Category> categories_list=new LinkedList<>();
   public CategoryManager(){
      categories_list= new LinkedList<>();
    }

	/**
	*El método crea una categoría y la añade a la lista de categorías
	*@params
	*category_id : Id de las categoría
	*category : Nombre de la categoría
	*/
	public  static void createCategory(Integer category_id, String category, Integer status){
		//System.out.println(getObject(category_id).toString());
		Category c= new Category(category_id, category, status);
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
  *Actualiza el id de la categoría
  *@Params:
  *id : Integer
  *nvoId : Integer
  */
  public static void updateIdCategory(Integer id, Integer nvoId){
    Category c= getCategory(id);
    if(c!=null){
      c.setCategoryId(nvoId);
    }
    System.out.println("Category doesn't exists");
  }


  /*
  *
  */
    public static void updateIdCategory(Integer id, String category){
      Category c= getCategory(id);
      if(c!=null){
        c.setCategory(category);
      }
      System.out.println("Category doesn't exists");
    }
	/**
	*Devuelve la categoría correspondiente al ID
	*@Returns
  *Category
	*/
	public  static Category getCategory(Integer category_id){
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
		Category fakeCategory= new Category(category_id);
		if(categories_list.contains(fakeCategory)){
				categories_list.remove(isMyObjectThere(fakeCategory, categories_list));
				System.out.println("Categoría removida con éxito");
				return;
		}
		System.out.println("No existe una categoría con el id ingresado");
	}
}
