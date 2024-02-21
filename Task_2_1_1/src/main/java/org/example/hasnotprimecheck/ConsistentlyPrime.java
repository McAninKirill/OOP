package org.example.hasnotprimecheck;

public class ConsistentlyPrime extends NotPrime {
    /**
     * Метод для последовательного способа
     *
     * @param array - список, который нам надо проверить
     * @return - true or false
     */
    public static boolean consistently(int[] array){
        for (int number:
                array){
            if (!isPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
