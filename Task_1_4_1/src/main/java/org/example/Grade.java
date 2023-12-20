package org.example;

public class Grade {
    private final String subjectName;
    private Integer grade;
    public Grade(String subjectName, Integer grade) {
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getSubject(){
        return subjectName;
    }

    public void setGrade(Integer newgrade) {
        grade  = newgrade;
    }

}
