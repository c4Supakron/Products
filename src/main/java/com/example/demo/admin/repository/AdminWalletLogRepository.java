package com.example.demo.admin.repository;

import com.example.demo.admin.entity.AdminWalletLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminWalletLogRepository extends CrudRepository<AdminWalletLogEntity, Long> {
}