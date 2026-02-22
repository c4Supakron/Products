package com.example.demo.common.controller;

import com.example.demo.common.dto.Product;
import com.example.demo.common.dto.ProductRes;
import com.example.demo.common.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductRes>> getAll() {
        List<ProductRes> productResList = productService.findAll();
        return  ResponseEntity.ok(productResList);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductRes> getById(@PathVariable Long id) {
        ProductRes productRes = productService.findById(id);
        return ResponseEntity.ok(productRes);
    }

}

