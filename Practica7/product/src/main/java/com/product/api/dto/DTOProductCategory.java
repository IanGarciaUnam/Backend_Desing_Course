package com.product.api.dto;

public class DTOProductCategory{

    private Integer product_id;
    private String gtin;
    private String product;
    private Double price;

    public DTOProductCategory(Integer product_id, String gtin, String product, Double price){
      this.product_id=product_id;
      this.gtin=gtin;
      this.product=product;
      this.price=price;

    }

    public DTOProductCategory(){
      super();
    }

    public Integer getProduct_id(){
      return this.product_id;
    }

    public void setProduct_id(Integer product_id){
      this.product_id=product_id;
    }

    public String getGTIN(){
      return this.gtin;
    }

    public void setGTIN(String gtin){
      this.gtin=gtin;
    }

    public String getProduct(){
      return this.product;
    }

    public void setProduct(String product){
       this.product=product;
    }

    public Double getPrice(){
      return this.price;
    }

    public void setPrice(Double price){
      this.price=price;
    }


}
