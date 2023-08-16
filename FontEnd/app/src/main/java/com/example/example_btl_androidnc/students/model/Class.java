package com.example.example_btl_androidnc.students.model;

public class Class {
    private String class_id;
    private String name_class;
    private String subject_id;
    private String teacher_id;
    private String start_date;
    private String end_date;
    private String status;
    private int number_ss;

    public Class(String class_id, String name_class, String subject_id, String teacher_id, String start_date, String end_date, String status, int number_ss) {
        this.class_id = class_id;
        this.name_class = name_class;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.number_ss = number_ss;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber_ss() {
        return number_ss;
    }

    public void setNumber_ss(int number_ss) {
        this.number_ss = number_ss;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id='" + class_id + '\'' +
                ", name_class='" + name_class + '\'' +
                ", subject_id='" + subject_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", status='" + status + '\'' +
                ", number_ss=" + number_ss +
                '}';
    }
}
