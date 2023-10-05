package org.example;

import javax.swing.text.PlainDocument;
import java.security.PrivateKey;

import static java.lang.Math.pow;

public class Polynomial {
    private int size;
    private int[] coeff;
    public int[] getCoeff() {
        return this.coeff.clone();
    }

    public Polynomial(int[] inputCoeff) {
        if (inputCoeff.length == 0) {
            coeff = new int[]{0};
        } else {
            coeff = new int[inputCoeff.length];
            for (int i = 0; i < inputCoeff.length; i++) {
                coeff[i] = inputCoeff[i];
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public Polynomial plus(Polynomial polynomial){
        int[] polynomial2_coeff = polynomial.getCoeff();
        int max_len = Math.max(this.size, polynomial.getSize());
        int[] res = new int[max_len];

        for(int i = 0; i < max_len; i ++){
            if (i < this.size){
                res[i] += polynomial2_coeff[i];
            }
            if(i < polynomial.getSize()){
                res[i] += coeff[i];
            }
        }
        return new Polynomial(res);
    }

    public Polynomial minus(Polynomial polynomial) {
        var negative_Coeff = polynomial.getCoeff();
        for (int i = 0; i < negative_Coeff.length; i++) {
            negative_Coeff[i] = -negative_Coeff[i];
        }

        return plus(new Polynomial(negative_Coeff));
    }


    public int evaluate(int x) {
        int ans = 0;
        for (int i = 0; i < this.size; i++) {
            ans += this.coeff[i] * (int) pow(x, i);
        }
        return ans;
    }

    public boolean equals(Polynomial polynomial) {
        boolean res = true;
        int len_1 = polynomial.getSize();
        int[] p_coeff = polynomial.getCoeff();

        if (len_1 != this.size){
            return false;
        }
        for (int i = 0; i < len_1; i++) {
            if (p_coeff[i] != this.coeff[i]) {
                res = false;
                break;
            }
        }

        return res;
    }

    public Polynomial differentiate(int n){
        int[] new_coeff = new int[coeff.length - n];

        int fact_n = 1;
        for(int i = 0; i < n; i++){
            fact_n *= i;
        }

        for(int i = 0; i < (coeff.length - n); i++){
            new_coeff[i] = coeff[i + n] * fact_n;
            fact_n *= i;
        }
        return new Polynomial(new_coeff);
    }

}
