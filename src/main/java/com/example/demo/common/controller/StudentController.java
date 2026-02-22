package com.example.demo.common.controller;

import com.example.demo.common.dto.Student;
import com.example.demo.common.dto.StudentRes;
import com.example.demo.common.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Student student) {
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentRes>> getAll() {
        List<StudentRes> studentResList = studentService.findAll();
        return  ResponseEntity.ok(studentResList);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<StudentRes> getById(@PathVariable Long id) {
        StudentRes studentRes = studentService.findById(id);
        return ResponseEntity.ok(studentRes);
    }

}
