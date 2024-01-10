package org.example;

public class Subject {
    private final String subjectName;
    private final Integer grade;
    public Subject(String subjectName, Integer grade) {
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getGrade() {
        return grade;
    }

}
