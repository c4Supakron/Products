package com.example.demo.product.dto;

import java.math.BigDecimal;

public class BuyReq {
    private Long productId;      // รหัสสินค้าที่ต้องการซื้อ
    private Integer quantity;    // จำนวนชิ้นที่ต้องการซื้อ
    private BigDecimal money;    // จำนวนเงินที่ลูกค้าจ่ายมา

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}