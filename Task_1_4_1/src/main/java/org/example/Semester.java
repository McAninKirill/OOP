package org.example;
import java.util.*;
public class Semester {
    private Integer numberOfSemester;
    public ArrayList<Grade> grades = new ArrayList<>();

    public Semester(Integer numberOfSemester){
        this.numberOfSemester = numberOfSemester;
    }

    public void AddGrade(Integer grade, String subjectname){
        Grade newgrade = new Grade(subjectname, grade);
        grades.add(newgrade);
    }

    public Double getAverageOfAllGrades(){
        return grades.stream().mapToDouble(Grade::getGrade).average().orElse(0.0);
    }
}
