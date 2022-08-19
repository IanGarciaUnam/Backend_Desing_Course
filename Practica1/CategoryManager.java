import java.util.LinkedList;
import java.util.Scanner;
import java.util.InputMismatchException;


class Category{
		private Integer category_id;
		private String category;

		Category(int category_id, String category){
			this.category_id=category_id;
			this.category=category;
		}

		public Integer getCategory_id(){
			return this.category_id;
		}

		public String getCategory(){
			return this.category;
		}

		@Override
		public String toString(){
			return "Category_id: "+category_id.toString()+" Category: "+ category;
	}
}

public class CategoryManager{


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
		}
	}catch(InputMismatchException ime){
		System.out.println("Debe insertar un número entre 1 y 4");
		sc.next();
	}

	}
}

	public static void createCategory(Integer category_id, String category){
		Category c=new Category(category_id, category);
		categories_list.add(c);
		System.out.println(c.toString());
	}

	public void getCategories(){
	for(Category cat: categories_list)
		System.out.println(cat.toString());
	}

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
