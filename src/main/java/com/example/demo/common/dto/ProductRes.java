package com.example.demo.common.dto;

public class ProductRes {

    private String product_code;
    private String product_name;
    private Integer category_id;
    private Integer supplier_id;
    private Float price;


    public void setProduct_code(String product_code) {this.product_code = product_code;}
    public String getProduct_code(){
        return product_code;
    }

    public void setProduct_name(String product_name) {this.product_name = product_name;}
    public String getProduct_name(){
        return product_name;
    }

    public void setCategory_id(Integer category_id) {this.category_id = category_id;}
    public Integer getCategory_id(){
        return category_id;
    }

    public void setSupplier_id(Integer supplier_id) {this.supplier_id = supplier_id;}
    public Integer getSupplier_id(){return supplier_id;}

    public void setPrice(Float price) {this.price = price;}
    public Float getPrice(){return price;}

}
