package com.product.api.entity;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
*Clase Category
*Modela una Categoría a traves de los
*parámetros  dados, category_id y category; id de la categoría y nombre de las categorías
*respectivamente
*/

@Entity
@Table(name="category")
public class Category {
  /*Decidí que el id debería ser un número entero sin límites de tamaño*/
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="category_id")
  private Integer category_id;
  /*La categoría quedará definida como un String, ya que permitirá ser más extensa e incluso explicativa*/
  @NotNull
  @Column(name="category")
  private String category;
  /*Status de la categoría*/
  @Column(name="status")
  @Min(value = 0, message="status must be 0 or 1")
  @Max(value = 1, message="status must be 0 or 1")
  @JsonIgnore
  private Integer status;

  /**
  *Constructor vacío de la clase
  */
  public Category(){

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
    return "Category [category_id="+this.category_id.toString()+", category ="+this.category + ", status ="+this.status +"]";
   }

}
