package com.product.api.entity;
/**
*Clase Category
*Modela una Categoría a traves de los
*parámetros  dados, category_id y category; id de la categoría y nombre de las categorías
*respectivamente
*/


public class Category implements Comparable<Category>{
  /*Decidí que el id debería ser un número entero sin límites de tamaño*/
  private Integer category_id;
  /*La categoría quedará definida como un String, ya que permitirá ser más extensa e incluso explicativa*/
  private String category;
  /*Status de la categoría*/
  private Integer status;

  /**
  *Constructor de la clase Category
  *
  *@params
  * int category_id : ID de la categoría
  *	String category : nombre de la categoría
  */
  public Category(int category_id, String category, Integer status){
    this.category_id=category_id;
    this.category=category;
    this.status=status;
  }
  /**
  *Constructor para categoría cuyo único parámetro
  *es el id de la Categoría
  *@Params
  *category_id
  */
  public Category(int category_id){
    this.category_id=category_id;
    this.category="NA";
    this.status=0;
  }
  /**
  *Devuelve el Id de la categoría
  *@returns
   *category_id
  */
  public Integer getCategory_id(){
    return this.category_id;
  }

  /**
  *Configura el nuevo id
  *
  **/
  public void setCategoryId(Integer id){
    this.category_id=id;
  }
  /*
  *Devuelve el nombre de la categoría
  *@returns
  *category
  */
  public String getCategory(){
    return this.category;
  }

  /**
  *Configura el nuevo category
  */
  public void setCategory(String category){
    this.category=category;
  }

  /**
  *Configura el status de la categoría
  *@Params
  *status:Integer
  */
  public void setStatus(Integer status){
  this.status=status;
  }

  /**
  *Devuelve el status de la categoría
  *@returns
  *stauts:Integer
  */
  public Integer getStatus(){
    return this.status;
  }


  @Override
  public boolean equals(Object c){
    if(c==this)return true;
    if(!(c instanceof Category))return false;
    Category cFromObject=(Category) c;
    return this.category_id.equals(cFromObject.getCategory_id()) || this.category.toLowerCase().equals(cFromObject.category.toLowerCase());
  }
  @Override
  public String toString(){
    return this.category_id.toString()+", "+this.category;
   }

  @Override
  public int compareTo(Category category){
    return category.category_id >= this.category_id ? -1 : 0;
  }
}
