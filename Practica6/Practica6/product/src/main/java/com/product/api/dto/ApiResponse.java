
package com.product.api.dto;

public class ApiResponse{

    private String message;

    public ApiResponse(String message){
      this.message=message;
    }

    public String getMessage(){
      return this.message;
    }

    public void setMessage(String message){
      this.message=message;
    }

    @Override
    public boolean equals(Object o){
      if (this == o)
        return true;
      if (o == null)
        return false;
      if (getClass() != o.getClass())
        return false;
    ApiResponse  api=(ApiResponse) o;
    return api.getMessage().equals(this.getMessage());
    }
}
