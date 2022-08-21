import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collection;


public class CategoryManager{


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

	/**
	*Clase CategoryManager, su objetivo es manejar a la clase Categoría
	*
	*
	*/

	private static LinkedList<Category> categories_list= new LinkedList<>();
	private static CategoryManager cm= new CategoryManager();
	public static void main(String[] args){

	Scanner sc=new Scanner(System.in);
	while(true){
		System.out.println("=================Práctica 1=====================");
		System.out.println("Crea una categoría y agregala a la lista ... [1]");
		System.out.println("Muestra todas las categorías registradas ... [2]");
		System.out.println("Busca una categoría por su id................[3]");
		System.out.println("Elimina una categoría por su id..............[4]");
		System.out.println("Salir........................................[0]");
		System.out.println("Limpiar pantalla.............................[5]");
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
		case 0:
			System.out.println("Saliendo");
			return;
		case 5:
			System.out.print("\033[H\033[2J");
			System.out.flush();
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
		//System.out.println(getObject(category_id).toString());
		//CategoryManager cm= new CategoryManager();
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
	*Devuelve un String con la lista de
	*
	*/
	public static void getCategories(){
		if(categories_list.isEmpty()){
			System.out.println("No existen categorías registradas");
			return;
		}
		String cats="";
		for(Category cat: categories_list)
				cats+=cat.toString()+"\n";
		System.out.println(cats);
	}

	/**
	*Devuelve la categoría correspondiente al ID
	*imprimiendola en pantalla
	*/
	public static void getCategory(Integer category_id){
		Category fakeCategory=cm.new Category(category_id);
		if(!categories_list.contains(fakeCategory)){
			System.out.println("No existe una categoría con el id ingresado");
			return;
		}
		System.out.println(isMyObjectThere(fakeCategory, categories_list));
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
	public static void deleteCategory(Integer category_id){
		Category fakeCategory=cm.new Category(category_id);
		if(categories_list.contains(fakeCategory)){
				categories_list.remove(isMyObjectThere(fakeCategory, categories_list));
				System.out.println("Categoría removida con éxito");
				return;
		}
		System.out.println("La categoría no se encuentra en el listado de categorías");
	}
}
