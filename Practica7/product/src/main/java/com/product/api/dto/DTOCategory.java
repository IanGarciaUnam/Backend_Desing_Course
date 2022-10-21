
package com.product.api.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
//import javax.persistence.Column;
//import javax.persistence.Table;

//@Table(name = "product")
public class DTOCategory{

    //@JsonProperty("category_id")
    //@Column(name = "category_id")
    private Integer category_id;


    public DTOCategory(Integer category_id){
      this.category_id=category_id;
    }

    public Integer getCategory_id(){
      return this.category_id;
    }

    public void setCategory_id(Integer category_id){
      this.category_id=category_id;
    }
}
