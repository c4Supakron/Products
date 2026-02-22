package com.example.demo.product.repository;

import com.example.demo.product.entity.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository <CategoriesEntity , Long> {
}
