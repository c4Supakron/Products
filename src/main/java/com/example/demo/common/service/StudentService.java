package com.example.demo.common.service;


import com.example.demo.common.dto.Student;
import com.example.demo.common.dto.StudentRes;
import com.example.demo.common.entity.StudentEntity;
import com.example.demo.common.repository.StudentRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

        public void saveStudent(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(student.getName());
        studentEntity.setStudentid(student.getStudentid());
        studentEntity.setTelnumber(student.getTelnumber());
        studentEntity.setMyclass(student.getmyclass());
        studentEntity.setStudyplan(student.getstudyplan());
        studentRepository.save(studentEntity);


    }

    public List<StudentRes> findAll() {
        List<StudentRes> studentResList = new ArrayList<>();
        List<StudentEntity> studentEntitys = (List<StudentEntity>) studentRepository.findAll();
        for(int i = 0; i < studentEntitys.size();i++) {
            StudentRes studentRes = new StudentRes();
            studentRes.setStudentid(studentEntitys.get(i).getStudentid());
            studentRes.setName(studentEntitys.get(i).getName());
            studentRes.setTelnumber(studentEntitys.get(i).getTelnumber());
            studentRes.setStudyplan(studentEntitys.get(i).getStudyplan());
            studentRes.setMyclass(studentEntitys.get(i).getMyclass());
            studentResList.add(studentRes);
        }
        return studentResList;
    }

    public StudentRes findById(Long id) {

        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found id = " + id));

        StudentRes res = new StudentRes();
        res.setStudentid(entity.getStudentid());
        res.setName(entity.getName());
        res.setTelnumber(entity.getTelnumber());
        res.setStudyplan(entity.getStudyplan());
        res.setMyclass(entity.getMyclass());

        return res;
    }
}

