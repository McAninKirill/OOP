package org.example;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
public class Polynomial {
    private final int size;
    private final int[] coeff;
    public int[] getCoeff() {
        return this.coeff.clone();
    }




    public Polynomial(int[] coeff_arr) {
        var n = coeff_arr.length;
        if (n == 0) {
            this.coeff = new int[]{0};
            this.size = 1;
        } else {
            this.size = n;
            this.coeff = coeff_arr;
        }
    }

    public int getSize() {
        return this.size;
    }

    public Polynomial plus(Polynomial polynomial){
        int[] polynomial2_coeff = polynomial.getCoeff();
        int len_1 = this.size;
        int len_2 = polynomial.getSize();
        int max_len = Math.max(len_1, len_2);
        int[] res = new int[max_len];

        for(int i = 0; i < max_len; i ++){
            if (i < len_2){
                res[i] += polynomial2_coeff[i];
            }
            if(i < len_1){
                res[i] += coeff[i];
            }
        }
        return new Polynomial(res);
    }

    public Polynomial minus(Polynomial polynomial){
        int[] polynomial2_coeff = polynomial.getCoeff();
        int len_1 = this.size;
        int len_2 = polynomial.getSize();
        int max_len = Math.max(len_1, len_2);
        int[] res = new int[max_len];

        for(int i = 0; i < max_len; i ++){
            if (i < len_2){
                res[i] -= polynomial2_coeff[i];
            }
            if(i < len_1){
                res[i] += coeff[i];
            }
        }
        return new Polynomial(res);
    }


    public int evaluate(int x) {
        int ans = 0;
        for (int i = 0; i < this.size; i++) {
            ans += this.coeff[i] * (int) pow(x, i);
        }
        return ans;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        boolean res = true;
        var polynomial = (Polynomial) obj;
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
        int new_len = coeff.length - n;

        if(new_len < 1){
            return new Polynomial(new int[]{0});
        }

        int[] new_coeff = new int[new_len];

        int fact_n = 1;

        for(int i = 0; i < new_len; i++){
            for(int k = i+1; k <= n+i; k++){
                fact_n = fact_n * k;
            }
            new_coeff[i] = coeff[i + n] * fact_n;
            fact_n = 1;
        }
        return new Polynomial(new_coeff);
    }

    @Override
    public String toString() {
        var ans = new StringBuilder();

        boolean is_it_first = true, empty = true;
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.coeff[i] != 0) {
                empty = false;
                if (!is_it_first) {
                    if (this.coeff[i] < 0) {
                        ans.append(" - ");
                    } else {
                        ans.append(" + ");
                    }
                } else {
                    if (this.coeff[i] < 0) {
                        ans.append("- ");
                    }
                    is_it_first = false;
                }
                if (i > 1) {
                    ans.append(abs(this.coeff[i])).append("x^").append(i);
                } else if (i == 1) {
                    ans.append(abs(this.coeff[i])).append("x");
                } else  {
                    ans.append(abs(this.coeff[i]));
                }
            }
        }

        if(empty){
            ans.append("0");
        }
        return ans.toString();
    }

    public Polynomial times(Polynomial polynomial) {
        int[] coef = polynomial.getCoeff();
        int[] new_Coef = new int[coeff.length + coef.length];

        for (int i = 0; i < coef.length; i++) {
            for (int j = 0; j < coeff.length; j++) {
                new_Coef[i + j] += coeff[j] * coef[i];
            }
        }
        return new Polynomial(new_Coef);
    }
}
