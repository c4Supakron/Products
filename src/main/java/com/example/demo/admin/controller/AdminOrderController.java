package com.example.demo.admin.controller;

import com.example.demo.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    private final AdminService adminService;

    public AdminOrderController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(adminService.getAllOrders());
    }

    @PutMapping("/{id}/ship")
    public ResponseEntity<String> shipOrder(@PathVariable Long id) {
        try {
            adminService.shipOrder(id);
            return ResponseEntity.ok("จัดส่งและโอนกำไรเข้ากระเป๋าตัวแทนสำเร็จ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeOrder(@PathVariable Long id) {
        adminService.completeOrder(id);
        return ResponseEntity.ok("เปลี่ยนสถานะออเดอร์เป็นเสร็จสมบูรณ์แล้ว");
    }
}