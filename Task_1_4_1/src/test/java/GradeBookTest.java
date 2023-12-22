
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class GradeBookTest {

    @Test
    void simpletest() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        gradeBook.AddGrade(1, 5, "OS");
        gradeBook.AddGrade(1, 3, "OS");
        gradeBook.AddGrade(2, 4, "Math");
        gradeBook.AddGrade(3, 5,"ML");
        assertEquals(4.0,gradeBook.AverageGrave());
    }

    @Test
    void testFalseGrant() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddGrade(i, 4, "1");
            gradeBook.AddGrade(i, 5, "2");
            gradeBook.AddGrade(i, 4, "3");
            gradeBook.AddGrade(i, 2, "4");
        }
        assertEquals(false, gradeBook.Grant());
    }

    @Test
    void testTrueGrant() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 5; i++) {
            gradeBook.AddGrade(i, 4, "1");
            gradeBook.AddGrade(i, 5, "2");
            gradeBook.AddGrade(i, 3, "3");
            gradeBook.AddGrade(i, 4, "4");
        }
        gradeBook.AddGrade(6, 5, "1");
        gradeBook.AddGrade(6, 5, "2");
        gradeBook.AddGrade(6, 4, "3");
        assertEquals(true, gradeBook.Grant());
    }

    @Test
    void testDiplom() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddGrade(i, 4, "1");
            gradeBook.AddGrade(i, 5, "2");
            gradeBook.AddGrade(i, 5, "3");
            gradeBook.AddGrade(i, 5, "4");
        }
        gradeBook.AddQualificationWork(5);
        assertEquals(true, gradeBook.RedDiplom());
    }

    @Test
    void testFalseDiplom() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 7; i++) {
            gradeBook.AddGrade(i, 4, "1");
            gradeBook.AddGrade(i, 5, "2");
            gradeBook.AddGrade(i, 5, "3");
            gradeBook.AddGrade(i, 5, "4");
        }

        gradeBook.AddGrade(8, 5, "5");
        gradeBook.AddGrade(8, 5, "6");
        gradeBook.AddGrade(8, 4, "7");
        gradeBook.AddGrade(8, 4, "8");

        gradeBook.AddQualificationWork(5);
        assertEquals(false, gradeBook.RedDiplom());
    }

    @Test
    void testFalse2Diplom() throws Exception{
        GradeBook gradeBook = new GradeBook("Kirill Makanin", "22216");
        for(Integer i = 1; i <= 8; i++) {
            gradeBook.AddGrade(i, 4, "1");
            gradeBook.AddGrade(i, 5, "2");
            gradeBook.AddGrade(i, 5, "3");
            gradeBook.AddGrade(i, 5, "4");
        }
        gradeBook.AddQualificationWork(4);
        assertEquals(false, gradeBook.RedDiplom());
    }
}
