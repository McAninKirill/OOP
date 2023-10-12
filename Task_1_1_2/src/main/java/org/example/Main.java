package org.example;

public class Main {
    public static void main(String[] args) {
        Polynomial p2 = new Polynomial(new int[]{1,2,3,4,5});
        System.out.println("Polynom p1: " + p2.toString());
        System.out.println("Dif: " + p2.differentiate(2).toString());
    }
}