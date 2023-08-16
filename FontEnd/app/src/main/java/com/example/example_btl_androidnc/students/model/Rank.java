package com.example.example_btl_androidnc.students.model;

public class Rank {
    private float midtermGrades;

    private float finalGrades;

    private float exams;

    public Rank(float midtermGrades, float finalGrades, float exams) {
        this.midtermGrades = midtermGrades;
        this.finalGrades = finalGrades;
        this.exams = exams;
    }

    public Rank() {
    }

    public float getMidtermGrades() {
        return midtermGrades;
    }

    public void setMidtermGrades(float midtermGrades) {
        this.midtermGrades = midtermGrades;
    }

    public float getFinalGrades() {
        return finalGrades;
    }

    public void setFinalGrades(float finalGrades) {
        this.finalGrades = finalGrades;
    }

    public float getExams() {
        return exams;
    }

    public void setExams(float exams) {
        this.exams = exams;
    }
}
