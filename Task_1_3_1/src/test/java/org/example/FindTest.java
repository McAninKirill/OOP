package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

/**
 * Tester class.
 */
public class FindTest {
    @Test
    public void basicTest() {
        FindSubstr name = new FindSubstr("java/org/example/simple.txt");
        List<Integer> answer =  name.findSubstr("bra");
        Assertions.assertEquals("", answer.toString());
    }
}