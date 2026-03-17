package com.example.demo.admin.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet_logs", schema = "public")
public class AdminWalletLogEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "wallet_id") private Long walletId;
    private BigDecimal amount;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getWalletId() { return walletId; } public void setWalletId(Long walletId) { this.walletId = walletId; }
    public BigDecimal getAmount() { return amount; } public void setAmount(BigDecimal amount) { this.amount = amount; }
}