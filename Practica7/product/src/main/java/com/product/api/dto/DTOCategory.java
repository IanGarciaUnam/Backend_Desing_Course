
package com.product.api.dto;
import javax.validation.constraints.NotNull;
import lombok.Data;
@Data
public class DTOCategory{

    @NotNull(message="category id is required")
    private int category_id;

    public DTOCategory(int category_id){
      this.category_id=category_id;
    }
    public DTOCategory(){
      super();
    }

    public int getCategory_id(){
      return this.category_id;
    }

    public void setCategory_id(int category_id){
      this.category_id=category_id;
    }
}
