package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository <ProductsEntity , Long> {
}
