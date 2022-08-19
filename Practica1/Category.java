import java.util.*;

Category{
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
	return this.category
	}

	@Override
	public String toString(){
	return "Category_id: "category_id.toString()+" Category: "+category;
	}
}
