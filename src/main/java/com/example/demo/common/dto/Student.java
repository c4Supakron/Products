package com.example.demo.common.dto;

public class Student {
    private String studentid;
    private String name;
    private String telnumber;
    private String studyplan;
    private String myclass;

    public void setStudentid(String studentid) {this.studentid = studentid;}
    public String getStudentid(){
        return studentid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }
    public String getTelnumber(){
        return  telnumber;
    }

    public void setStudyplan(String studyplan) {
        this.studyplan = studyplan;
    }
    public String getstudyplan(){
        return  studyplan;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }
    public String getmyclass(){
        return  myclass;
    }


}
