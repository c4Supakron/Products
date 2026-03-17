package com.example.demo.admin.repository;

import com.example.demo.admin.entity.AdminOrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrderRepository extends CrudRepository<AdminOrderEntity, Long> {
}