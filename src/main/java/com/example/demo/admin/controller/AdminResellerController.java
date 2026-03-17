package com.example.demo.admin.controller;

import com.example.demo.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/resellers")
public class AdminResellerController {

    private final AdminService adminService;

    public AdminResellerController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllResellers() {
        return ResponseEntity.ok(adminService.getAllResellers());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveReseller(@PathVariable Long id) {
        adminService.updateResellerStatus(id, "approved");
        return ResponseEntity.ok("อนุมัติตัวแทนสำเร็จ");
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectReseller(@PathVariable Long id) {
        adminService.updateResellerStatus(id, "rejected");
        return ResponseEntity.ok("ปฏิเสธตัวแทนสำเร็จ");
    }
}