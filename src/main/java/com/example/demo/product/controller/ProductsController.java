package com.example.demo.product.controller;

import com.example.demo.product.dto.*;
import com.example.demo.product.dto.ProductsReq;
import com.example.demo.product.service.CategoriesService;
import com.example.demo.product.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/create1")
    public ResponseEntity<Void> create(@RequestBody ProductsReq productsReq) {
        productsService.saveProducts(productsReq);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read1")
    public ResponseEntity<List<ProductsReq>> getAlL() {
        return ResponseEntity.ok(productsService.findall());
    }

    @PostMapping("/buy")
    public ResponseEntity<BuyRes> buyProduct(@RequestBody BuyReq buyreq) {
        try {
            // เรียกใช้ Service สำหรับการซื้อ
            BuyRes result = productsService.buyProduct(buyreq);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            // ถ้าเกิด Error (เช่น สต็อกไม่พอ หรือเงินไม่พอ)
            BuyRes errorRes = new BuyRes();
            errorRes.setMessage(e.getMessage());
            errorRes.setTotalPrice(java.math.BigDecimal.ZERO);

            // ----> เพิ่มบรรทัดนี้ (ถ้า error ให้สต็อกที่คืนกลับไปเป็น 0) <----
            errorRes.setRemainingStock(0);

            return ResponseEntity.badRequest().body(errorRes);
        }
    }
}
