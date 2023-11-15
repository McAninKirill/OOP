package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.util.List;


public class FindSubstrTest{
    @Test
    public void basicTest ()  throws IOException{
        FindSubstr name = new FindSubstr("./src/test/java/org/example/simple.txt");
        List<Integer> answer =  name.findSubstr("bra");
        Assertions.assertEquals("[9, 40]", answer.toString());
    }

    @Test
    public void bigTest ()  throws IOException{
        FindSubstr name = new FindSubstr("./src/test/java/org/example/big.txt");
        List<Integer> answer =  name.findSubstr("abcdefg");
        Assertions.assertEquals("[432, 753, 1730]", answer.toString());
    }

    @Test
    public void nullTest ()  throws IOException{
        FindSubstr name = new FindSubstr("./src/test/java/org/example/null.txt");
        List<Integer> answer =  name.findSubstr("something");
        Assertions.assertEquals("[]", answer.toString());
    }

    @Test
    public void rusTest ()  throws IOException{
        FindSubstr name = new FindSubstr("./src/test/java/org/example/rus.txt");
        List<Integer> answer =  name.findSubstr("балалайка");
        Assertions.assertEquals("[40]", answer.toString());
    }// It doesn't work. Why?!
}