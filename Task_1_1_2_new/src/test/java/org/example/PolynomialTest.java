package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {

    @Test
    public void plus_Test(){
        Polynomial polynomial1 = new Polynomial(new int[]{1,2,3,4});
        Polynomial polynomial2 = new Polynomial(new int[]{5,6,7,8,9});
        Polynomial polynomial3 = new Polynomial(new int[]{6,8,10,12,9});
        assertEquals(polynomial1.plus(polynomial2).toString(), polynomial3.toString());
    }

    @Test
    public void minus_Test(){
        Polynomial polynomial2 = new Polynomial(new int[]{1,2,3,4});
        Polynomial polynomial1 = new Polynomial(new int[]{5,6,7,8,9});
        Polynomial polynomial3 = new Polynomial(new int[]{4,4,4,4,9});
        assertEquals(polynomial1.minus(polynomial2).toString(), polynomial3.toString());
    }

    @Test
    public void equals_true_Test(){
        Polynomial polynomial1 = new Polynomial(new int[]{1,2,3,4});
        Polynomial polynomial2 = new Polynomial(new int[]{1,2,3,4});
        assertEquals(polynomial1.equals(polynomial2),true);
    }

    @Test
    public void equals_false_Test(){
        Polynomial polynomial1 = new Polynomial(new int[]{1,2,3,4});
        Polynomial polynomial2 = new Polynomial(new int[]{2,1,3,4});
        assertEquals(polynomial1.equals(polynomial2),false);
    }

    @Test
    public void evaluate_Test(){
        Polynomial polynomial = new Polynomial(new int[]{4,3,2,1});
        assertEquals(polynomial.evaluate(3), 58);
    }

    @Test
    public void differentiate_Test(){
        Polynomial polynomial = new Polynomial(new int[]{5,4,3,2,1});
        Polynomial polynomial_dif = new Polynomial(new int[]{6,12,24});
        assertEquals(polynomial.differentiate(2).toString(), polynomial_dif.toString());
    }

    @Test
    public void times_Test(){
        Polynomial p1 = new Polynomial(new int[]{1,2,3});
        Polynomial p2 = new Polynomial(new int[]{1,2,3,4});
        Polynomial p3 = new Polynomial(new int[]{1,4,10,16,17,12});
        assertEquals(p1.times(p2).toString(), p3.toString());
    }
}