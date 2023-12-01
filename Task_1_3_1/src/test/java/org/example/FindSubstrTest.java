package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


import java.net.URL;
import java.util.List;

import static org.example.FindSubstr.findSubstr;


public class FindSubstrTest{
    @Test
    public void basicTest ()  throws RuntimeException{


        List<Integer> answer =  findSubstr("bra", "simple.txt");
        Assertions.assertEquals("[9, 40]", answer.toString());
    }

    @Test
    public void bigTest ()  throws RuntimeException{
        List<Integer> answer =  findSubstr("abcdefg", "big.txt");
        Assertions.assertEquals("[3002]", answer.toString());
    }

    @Test
    public void nullTest ()  throws RuntimeException{
        List<Integer> answer =  findSubstr("something", "null.txt");
        Assertions.assertEquals("[]", answer.toString());
    }

    @Test
    public void rusTest ()  throws RuntimeException{
        String str = "день";
        byte[] bytes = str.getBytes();

        String substring = new String(bytes, StandardCharsets.UTF_8);
        List<Integer> answer =  findSubstr(substring , "rus.txt");
        Assertions.assertEquals("[9]", answer.toString());
    }// It doesn't work. Why?!


    public void hugeTest () throws RuntimeException{

        URL url = this.getClass().getClassLoader().getResource("");
        String resourcesPath = new File(url.getFile()).getPath();
        File file = new File(resourcesPath, "example.txt");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            for (int i = 0; i < 1000000000; i++) {
                writer.write("Something on the line of a huge file");
                if(i == 54342090){
                   writer.write("answer");
                }
            }


            List<Integer> result =  findSubstr("answer", "example.txt");
            Assertions.assertEquals("[1956315276]", result.toString());

            if (!file.delete()) {
                System.err.println("Unable to delete the test file.");
            }
         else {
                System.err.println("Unable to get the resources directory.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
