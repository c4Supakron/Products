package com.example.demo.product.dto;

import java.math.BigDecimal;

public class BuyRes {
    private String message;
    private BigDecimal totalPrice;
    private Integer remainingStock;
    private BigDecimal change; // <--- 🌟 เพิ่มตัวแปรเก็บเงินทอน

    // --- Getters & Setters ---
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public Integer getRemainingStock() { return remainingStock; }
    public void setRemainingStock(Integer remainingStock) { this.remainingStock = remainingStock; }

    public BigDecimal getChange() { return change; }
    public void setChange(BigDecimal change) { this.change = change; }
}