package com.example.demo.admin.service;

import com.example.demo.admin.dto.*;
import com.example.demo.admin.entity.*;
import com.example.demo.admin.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final AdminProductRepository productRepo;
    private final AdminUserRepository userRepo;
    private final AdminOrderRepository orderRepo;
    private final AdminWalletRepository walletRepo;
    private final AdminWalletLogRepository walletLogRepo;

    public AdminService(AdminProductRepository p, AdminUserRepository u, AdminOrderRepository o, AdminWalletRepository w, AdminWalletLogRepository wl) {
        this.productRepo = p;
        this.userRepo = u;
        this.orderRepo = o;
        this.walletRepo = w;
        this.walletLogRepo = wl;
    }

    // --- ระบบ Login ---
    public AdminUserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // --- 1. จัดการสินค้า (Products) ---
    public void addProduct(AddProductReq req) {
        AdminProductEntity p = new AdminProductEntity();
        p.setName(req.getName());
        // ลบ Description และ ImageUrl ออกแล้ว
        p.setCostPrice(req.getCostPrice());
        p.setMinPrice(req.getMinPrice());
        p.setStock(req.getStock());
        productRepo.save(p);
    }

    public List<AdminProductEntity> getAllProducts() {
        List<AdminProductEntity> list = new ArrayList<>();
        productRepo.findAll().forEach(list::add);
        return list;
    }

    public void editProduct(Long id, AddProductReq req) {
        AdminProductEntity p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ไม่พบสินค้าที่ต้องการแก้ไข"));
        p.setName(req.getName());
        // ลบ Description และ ImageUrl ออกแล้ว
        p.setCostPrice(req.getCostPrice());
        p.setMinPrice(req.getMinPrice());
        p.setStock(req.getStock());
        productRepo.save(p);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    // --- 2. อนุมัติตัวแทน (Resellers) ---
    public List<AdminUserEntity> getAllResellers() {
        List<AdminUserEntity> list = new ArrayList<>();
        userRepo.findAll().forEach(u -> {
            if ("reseller".equals(u.getRole())) list.add(u);
        });
        return list;
    }

    public void updateResellerStatus(Long id, String status) {
        AdminUserEntity u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("ไม่พบตัวแทน"));
        u.setStatus(status);
        userRepo.save(u);
    }

    // --- 3. จัดการออเดอร์ & โอนกำไร ---
    public List<AdminOrderEntity> getAllOrders() {
        List<AdminOrderEntity> list = new ArrayList<>();
        orderRepo.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public void shipOrder(Long orderId) {
        AdminOrderEntity order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("ไม่พบออเดอร์"));
        if (!"pending".equals(order.getStatus())) throw new RuntimeException("ออเดอร์นี้ไม่ได้รอดำเนินการ");

        order.setStatus("shipped");
        orderRepo.save(order);

        Long resellerUserId = order.getShopId();
        AdminWalletEntity wallet = walletRepo.findByUserId(resellerUserId);

        if (wallet != null) {
            wallet.setBalance(wallet.getBalance().add(order.getResellerProfit()));
            walletRepo.save(wallet);

            AdminWalletLogEntity log = new AdminWalletLogEntity();
            log.setWalletId(wallet.getId());
            log.setAmount(order.getResellerProfit());
            walletLogRepo.save(log);
        }
    }

    // --- เมธอดที่ทำให้หายแดง (Complete Order) ---
    public void completeOrder(Long orderId) {
        AdminOrderEntity order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("ไม่พบออเดอร์"));
        order.setStatus("completed");
        orderRepo.save(order);
    }

    // --- 4. แดชบอร์ด (Dashboard) ---
    public AdminDashboardRes getDashboardStats() {
        AdminDashboardRes res = new AdminDashboardRes();

        // คำนวณฝั่งออเดอร์
        orderRepo.findAll().forEach(o -> {
            res.setTotalOrders(res.getTotalOrders() + 1);

            // เพิ่ม: นับออเดอร์รอดำเนินการ
            if ("pending".equals(o.getStatus())) {
                res.setPendingOrders(res.getPendingOrders() + 1);
            }

            if ("shipped".equals(o.getStatus()) || "completed".equals(o.getStatus())) {
                res.setTotalSales(res.getTotalSales().add(o.getTotalAmount()));
                res.setTotalProfit(res.getTotalProfit().add(o.getResellerProfit()));
            }
        });

        // คำนวณฝั่งตัวแทน
        userRepo.findAll().forEach(u -> {
            if ("reseller".equals(u.getRole())) {
                // เพิ่ม: นับจำนวนตัวแทนทั้งหมด (ที่อนุมัติแล้ว)
                if ("approved".equals(u.getStatus())) {
                    res.setTotalResellers(res.getTotalResellers() + 1);
                }
                // นับตัวแทนรออนุมัติ
                if ("pending".equals(u.getStatus())) {
                    res.setPendingResellers(res.getPendingResellers() + 1);
                }
            }
        });

        return res;
    }
}