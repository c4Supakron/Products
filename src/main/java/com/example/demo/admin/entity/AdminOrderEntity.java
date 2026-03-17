package com.example.demo.admin.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders", schema = "public")
public class AdminOrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shop_id") private Long shopId;
    @Column(name = "total_amount") private BigDecimal totalAmount;
    @Column(name = "reseller_profit") private BigDecimal resellerProfit;
    private String status; // "pending", "shipped"

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getShopId() { return shopId; } public void setShopId(Long shopId) { this.shopId = shopId; }
    public BigDecimal getTotalAmount() { return totalAmount; } public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getResellerProfit() { return resellerProfit; } public void setResellerProfit(BigDecimal resellerProfit) { this.resellerProfit = resellerProfit; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}