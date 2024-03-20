package org.example.hasnotprimecheck;

import java.util.Arrays;

public class ConsistentlyPrime extends NotPrime {
    /**
     * Метод для последовательного способа
     *
     * @param array - список, который нам надо проверить
     * @return - true or false
     */
    @Override
    public boolean hasPrime(int[] array){
        return Arrays.stream(array).anyMatch(num -> !isPrime(num));
    }
}
