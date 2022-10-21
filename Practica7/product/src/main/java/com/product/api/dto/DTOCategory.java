
package com.product.api.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class DTOCategory{

    @NotBlank(message="category id is required")
    private int category_id;

    public DTOCategory(int category_id){
      this.category_id=category_id;
    }

    public int getCategory_id(){
      return this.category_id;
    }

    public void setCategory_id(int category_id){
      this.category_id=category_id;
    }
}
