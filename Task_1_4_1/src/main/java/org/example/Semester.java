package org.example;
import java.util.*;
public class Semester {
    private Integer numberOfSemester;
    private Integer amountOfThreeAndTwo;
    public ArrayList<Subject> grades = new ArrayList<>();

    public Semester(Integer numberOfSemester){
        this.numberOfSemester = numberOfSemester;
        amountOfThreeAndTwo = 0;
    }

    public void AddGrade(Integer grade, String subjectname){
        if (grade < 4) {
            amountOfThreeAndTwo += 1;
        }
        if(grades.stream().
                filter(sub -> subjectname.equals(sub.getSubjectName())).count() == 1){
            var subject = this.grades.stream().
                    filter(sub -> subjectname.equals(sub.getSubjectName())).findFirst();

            subject.ifPresent(grades :: remove);
            this.grades.add(new Subject(subjectname, grade));
        }
        else {
            Subject newsubject = new Subject(subjectname, grade);
            grades.add(newsubject);
        }
    }


    public Integer getBadGrades(){return amountOfThreeAndTwo;}

}
