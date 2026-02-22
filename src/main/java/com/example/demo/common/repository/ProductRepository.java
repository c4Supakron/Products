package com.example.demo.common.repository;

import com.example.demo.common.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <ProductEntity , Long> {
}
