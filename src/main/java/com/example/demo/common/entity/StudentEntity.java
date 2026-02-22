package com.example.demo.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student" , schema = "public")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "studentid")
    private  String studentid;

    @Column(name = "name")
    private  String name;

    @Column(name = "telnumber")
    private  String telnumber;

    @Column(name = "studyplan")
    private  String studyplan;

    @Column(name = "myclass")
    private  String myclass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getStudyplan() {
        return studyplan;
    }

    public void setStudyplan(String studyplan) {
        this.studyplan = studyplan;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }
}


