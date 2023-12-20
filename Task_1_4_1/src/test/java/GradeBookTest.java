
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class GradeBookTest {

    @Test
    void simpletest() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        gradeBook.AddSemester(1);
        gradeBook.AddSemester(2);
        gradeBook.AddSemester(3);
        gradeBook.AddGrade(1, 5, "OOP");
        gradeBook.AddGrade(1, 2, "OS");
        gradeBook.AddGrade(2, 4, "Math");
        gradeBook.AddGrade(3, 5,"ML");
        assertEquals(4.0,gradeBook.AverageGrave());
    }

    @Test
    void testFalseGrant() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddSemester(i);
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 2, "SMTH");
        }
        assertEquals(false, gradeBook.Grant());
    }

    @Test
    void testTrueGrant() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddSemester(i);
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 4, "SMTH");
        }
        assertEquals(true, gradeBook.Grant());
    }

    @Test
    void testDiplom() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddSemester(i);
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
        }
        gradeBook.AddQualificationWork(5);
        assertEquals(true, gradeBook.RedDiplom());
    }

    @Test
    void testFalseDiplom() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddSemester(i);
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
        }
        gradeBook.AddQualificationWork(4);
        assertEquals(false, gradeBook.RedDiplom());
    }

    @Test
    void testFalse2Diplom() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddSemester(i);
            gradeBook.AddGrade(i, 4, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            gradeBook.AddGrade(i, 5, "SMTH");
            if(i == 3){
                gradeBook.AddGrade(i, 3, "OS");
            }
        }
        gradeBook.AddQualificationWork(5);
        assertEquals(false, gradeBook.RedDiplom());
    }
}
