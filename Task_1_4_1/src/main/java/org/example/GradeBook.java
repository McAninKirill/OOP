package org.example;


import java.util.*;
public class GradeBook {
    private final String fullName;
    private final String group;

    private ArrayList<String> namesOfAllSubjects = new ArrayList<>();
    private Integer amountOfThreeAndTwo;
    private Integer qualificationWork;
    private Integer lastSemester;
    public ArrayList<Semester> semesters = new ArrayList<>();

    public GradeBook(String fullName, String group){
        this.fullName = fullName;
        this.group = group;
        amountOfThreeAndTwo = 0;
        lastSemester = 0;
        semesters = new ArrayList<>();
    }

    public Boolean Grant(){
        Semester semester = semesters.get(lastSemester - 1);
        return semester.getBadGrades() == 0;
    }

    public Double AverageGrave(){

        Integer amount = 0, sum = 0;

        for (String sub: namesOfAllSubjects){

            for(int i = 0; i < lastSemester; i++) {
                amount += (int)
                        semesters.get(i).grades.stream().
                                filter(x -> sub.equals(x.getSubjectName())).count();
                sum += (int) semesters.get(i).grades.stream().
                        filter(x -> sub.equals(x.getSubjectName())).
                        mapToInt(Subject::getGrade).average().orElse(0.0);
            }
        }
        return (double) sum/amount;
    }

    public void AddQualificationWork(Integer grade) throws Exception{
        if (grade < 2 || grade > 5) {
            throw new Exception("Grade must be between 2 and 5.");
        }
        this.qualificationWork =grade;
    }

    public void AddGrade(Integer numberOfSemester, Integer grade, String subjectName) throws Exception{
        if (grade < 2 || grade > 5) {
            throw new Exception("Grade must be between 2 and 5.");
        }

        if (numberOfSemester < 1 || numberOfSemester > 8){
            throw new Exception("Number of semester must be between 1 and 8.");
        }

        if(grade < 4){
            amountOfThreeAndTwo += 1;
        }

        if(namesOfAllSubjects.stream().noneMatch(sub -> subjectName.equals(sub))){
            namesOfAllSubjects.add(subjectName);
            /*
            checking for subject existence
             */
        }

        if (numberOfSemester > lastSemester){
            /*
            if this semester doesn't exist, then we add new semesters
             */
            for(int i = lastSemester + 1; i <= numberOfSemester; i++) {
                Semester newSemester = new Semester(i);
                semesters.add(newSemester);
            }
            lastSemester = numberOfSemester;
        }

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

        double aver = 0.0;
        int sum, amount, count = 0;
        for (String sub: namesOfAllSubjects){
            amount = 0;
            sum = 0;
            for(int i = 0; i < 8; i++) {
                amount += (int)
                        semesters.get(i).grades.stream().
                                filter(x -> sub.equals(x.getSubjectName())).count();
                sum += (int) semesters.get(i).grades.stream().
                        filter(x -> sub.equals(x.getSubjectName())).
                        mapToInt(Subject::getGrade).average().orElse(0.0);
            }
            aver += (double) sum/amount;
            count += 1;

        }

        aver = aver / count;

        return aver >= 4.75 && this.qualificationWork == 5;
    }
}
