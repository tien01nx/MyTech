package com.example.example_btl_androidnc.students.model;

public class Subject {
    private String Subject_id;
    private String name_subject;

    public Subject(String subject_id, String name_subject) {
        Subject_id = subject_id;
        this.name_subject = name_subject;
    }

    public String getSubject_id() {
        return Subject_id;
    }

    public void setSubject_id(String subject_id) {
        Subject_id = subject_id;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "Subject_id='" + Subject_id + '\'' +
                ", name_subject='" + name_subject + '\'' +
                '}';
    }
}
