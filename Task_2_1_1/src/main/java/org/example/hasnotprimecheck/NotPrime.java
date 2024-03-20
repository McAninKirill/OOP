package org.example.hasnotprimecheck;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * От этого класса будут наследоваться три способа.
 */
abstract class NotPrime{
    /**
     * Метода для того, чтобы узнать, простое ли число.
     * @param num - число
     * @return - true or false
     */
    public static boolean isPrime(int num) throws IllegalArgumentException {
        if (num <= 1) {
            if(num  <= 0){
                throw new IllegalArgumentException();
            }
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++){
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean hasPrime(int[] arr);
}
