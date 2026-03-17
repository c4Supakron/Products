package com.example.demo.admin.repository;

import com.example.demo.admin.entity.AdminProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProductRepository extends CrudRepository<AdminProductEntity, Long> {
}