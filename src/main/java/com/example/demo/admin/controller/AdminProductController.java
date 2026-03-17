package com.example.demo.admin.controller;

import com.example.demo.admin.dto.AddProductReq;
import com.example.demo.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final AdminService adminService;

    public AdminProductController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody AddProductReq req) {
        if (req.getMinPrice().compareTo(req.getCostPrice()) < 0) {
            return ResponseEntity.badRequest().body("ราคาขั้นต่ำต้องมากกว่าหรือเท่ากับราคาทุน");
        }
        adminService.addProduct(req);
        return ResponseEntity.ok("เพิ่มสินค้าเรียบร้อยแล้ว");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(adminService.getAllProducts());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editProduct(@PathVariable Long id, @RequestBody AddProductReq req) {
        if (req.getMinPrice().compareTo(req.getCostPrice()) < 0) {
            return ResponseEntity.badRequest().body("ราคาขั้นต่ำต้องมากกว่าหรือเท่ากับราคาทุน");
        }
        try {
            adminService.editProduct(id, req);
            return ResponseEntity.ok("แก้ไขสินค้าเรียบร้อยแล้ว");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            adminService.deleteProduct(id);
            return ResponseEntity.ok("ลบสินค้าเรียบร้อยแล้ว");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ไม่สามารถลบสินค้าได้ เนื่องจากมีออเดอร์ค้างอยู่");
        }
    }
}