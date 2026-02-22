package com.example.demo.product.controller;

import com.example.demo.product.dto.CategoriesReq;
import com.example.demo.product.service.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CategoriesReq categoriesReq) {
        categoriesService.saveCategories(categoriesReq);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read")
    public ResponseEntity<List<CategoriesReq>> getAlL() {
        return ResponseEntity.ok(categoriesService.findall());
    }


}
