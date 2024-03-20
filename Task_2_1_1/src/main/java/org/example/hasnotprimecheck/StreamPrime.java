package org.example.hasnotprimecheck;

import java.util.Arrays;

public class StreamPrime extends NotPrime{
    /**
     * Метод для параллельного способа с применением Stream
     *
     * @param array - список, который нам надо проверить
     * @return true or else
     */
    public static boolean parallelStream(int[] array){
        return Arrays.stream(array).parallel().anyMatch(num -> {
            try {
                return !isPrime(num);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
