package org.example;


import java.util.*;
public class GradeBook {
    private String fullName;
    private String group;

    private Integer amountOfThreeAndTwo;
    private Integer qualificationWork;
    private Integer amountOfAllGrade;
    private Integer sumOfAllGrade;
    public ArrayList<Semester> semesters = new ArrayList<>();

    public GradeBook(String fullName, String group){
        this.fullName = fullName;
        this.group = group;
        amountOfThreeAndTwo = 0;
        amountOfAllGrade = 0;
        sumOfAllGrade = 0;
        semesters = new ArrayList<>();
    }

    public Boolean Grant(){
        return amountOfThreeAndTwo == 0;
    }

    public Double AverageGrave(){
        return (double) sumOfAllGrade/amountOfAllGrade;
    }

    public void AddSemester(Integer numberOfSemester) throws Exception{
        if (numberOfSemester < 1 || numberOfSemester > 8) {
            throw new Exception("Number of semester must be between 1 and 8.");
        }
        Semester newSemester = new Semester(numberOfSemester);
        semesters.add(newSemester);
    }

    public void AddQualificationWork(Integer grade){this.qualificationWork =grade;}

    public void AddGrade(Integer numberOfSemester, Integer grade, String subjectName) throws Exception{
        if (grade < 2 || grade > 5) {
            throw new Exception("Grade must be between 2 and 5.");
        }

        if(grade < 4){
            amountOfThreeAndTwo += 1;
        }

        sumOfAllGrade += grade;
        amountOfAllGrade += 1;

        Semester semester = semesters.get(numberOfSemester - 1);
        semester.AddGrade(grade, subjectName);
    }

    public boolean RedDiplom() throws Exception{
        if(amountOfThreeAndTwo > 0){
            return false;
        }

        if(this.qualificationWork == null){
            throw new Exception("You forgot to add a grave of qualification work");
        }
        Double averageGrade = semesters.get(7).getAverageOfAllGrades();
        return averageGrade >= 4.75 && this.qualificationWork ==5;
    }
}
