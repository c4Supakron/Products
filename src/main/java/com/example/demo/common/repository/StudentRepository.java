package com.example.demo.common.repository;

import com.example.demo.common.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends CrudRepository <StudentEntity, Long > {


}
