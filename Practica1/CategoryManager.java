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
		public String toString(){
			return "Category_id: "+category_id.toString()+" Category: "+ category;
	}
}

public class CategoryManager{
	/**
	*Clase CategoryManager, su objetivo es manejar a la clase categoría
	*
	*
	*/
	private static LinkedList<Category> categories_list= new LinkedList<>();

	public static void main(String[] args){

	Scanner sc=new Scanner(System.in);
	while(true){
		//System.out.print("\033[H\033[2J");
   	//System.out.flush();
		System.out.println("=================Práctica 1=====================");
		System.out.println("Crea una categoría y agregala a la lista ... [1]");
		System.out.println("Muestra todas las categorías registradas ... [2]");
		System.out.println("Busca una categoría por su id................[3]");
		System.out.println("Elimina una categoría por su id..............[4]");

		try{
		int opt =sc.nextInt();

		switch(opt){
		case 1:
			System.out.println("Inserta el category Id:\n");
			Integer categoryId=sc.nextInt();
			System.out.println("Inserta la categoría");
			String category=sc.next();
			createCategory(categoryId, category);
			break;
		case 2:
			getCategories();
			break;
		case 3:
			System.out.println("Ingresa ID de la categoría");
			Integer categoryID=sc.nextInt();
			getCategory(categoryID);
			break;
		case 4:
			System.out.println("Ingrese ID de la categoría que desea que sea eliminada");
			Integer catID=sc.nextInt();
			deleteCategory(catID);
			break;
		default:
			System.out.println("SELECCIONE ALGUNA DE LAS OPCIONES DISPONIBLES");
			break;
		}
	}catch(InputMismatchException ime){
		System.out.println("Debe insertar un número entre 1 y 4");
		sc.next();
	}

	}
}

	/**
	*El método crea una categoría y la añade a la lista de categorías
	*@params
	*category_id : Id de las categoría
	*category : Nombre de la categoría
	*/
	public static void createCategory(Integer category_id, String category){
		if(getObject(category_id)!=null){
			System.out.println("Ya existe una categoría con el id registrado");
			return;
		}
		Category c=new Category(category_id, category);
		categories_list.add(c);
		System.out.println(c.toString());
	}

	/**
	*Devuelve un String con la lista de
	*
	*/
	public void getCategories(){
		String cats="";
		for(Category cat: categories_list)
				cats+=cat.toString()+"\n";
		System.out.println(cats);
	}

	/**
	*Devuelve la categoría correspondiente al ID
	*
	*/
	public void getCategory(Integer category_id){
	for(Category cat: categories_list){
		if(cat.getCategory_id()==category_id){
			System.out.println(cat.toString());
		}
	}
	System.out.printf("No se encontró el objeto con Category_id: %d",category_id);

	}
	private Category getObject(Integer category_id){
		for(Category cat: categories_list)
			if(cat.getCategory_id()==category_id)
				return cat;
		return null;
	}

	public void deleteCategory(Integer category_id){
		Category cat=getObject(category_id);
		if(cat!=null){
			categories_list.remove(cat);
		}
	}






}
