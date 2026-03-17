package com.example.demo.admin.dto;

import java.math.BigDecimal;

public class AdminDashboardRes {
    private BigDecimal totalSales = BigDecimal.ZERO;
    private BigDecimal totalProfit = BigDecimal.ZERO;
    private int totalOrders = 0;
    private int pendingResellers = 0;

    // --- 2 ตัวแปรที่เพิ่มใหม่ตามเอกสารหน้า 5 ---
    private int pendingOrders = 0;
    private int totalResellers = 0;

    // --- Getters and Setters ทั้งหมด ---
    public BigDecimal getTotalSales() { return totalSales; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }

    public BigDecimal getTotalProfit() { return totalProfit; }
    public void setTotalProfit(BigDecimal totalProfit) { this.totalProfit = totalProfit; }

    public int getTotalOrders() { return totalOrders; }
    public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }

    public int getPendingResellers() { return pendingResellers; }
    public void setPendingResellers(int pendingResellers) { this.pendingResellers = pendingResellers; }

    // Getter/Setter ของใหม่
    public int getPendingOrders() { return pendingOrders; }
    public void setPendingOrders(int pendingOrders) { this.pendingOrders = pendingOrders; }

    public int getTotalResellers() { return totalResellers; }
    public void setTotalResellers(int totalResellers) { this.totalResellers = totalResellers; }
}