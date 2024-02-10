package org.example;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Arrays;

/*
 * В одном классе будут три способа
 */
public class NotPrime {
    /*
     * Метода для того, чтобы узнать, простое ли число
     * @param number - число
     * @return - true or false
     */
    private static boolean isPrime(int num) throws Exception{
        if (num <= 1) {
            if(num  <= 0){
                throw new Exception("A number must be positive");
            }
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    /*
    * Метод для последовательного способа
    *
    * @param array - список, который нам надо проверить
    * @return - true or false
    */
    public static boolean Consistently(int[] array)throws Exception{
        for (int number:
             array) {
            if(!isPrime(number)){
                return true;
            }
        }
        return false;
    }

    /*
    * Метод для параллельного способа с применением Threads
    *
    * @param array - список, который нам надо проверить
    * @param numThreads - число потоков
    * @return - true or false
    */
    public static boolean ParallelThread(int[] array, int numThreads) throws Exception {
        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        Thread[] threads = new Thread[numThreads];
        int chunkSize = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    try {
                        if (!isPrime(array[j])) {
                            hasNonPrime.set(true);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return hasNonPrime.get();
    }

    /*
    * Метод для параллельного способа с применением Stream
    *
    * @param array - список, который нам надо проверить
    * @return true or else
    */
    public static boolean ParallelStream(int[] array) throws Exception{
        return Arrays.stream(array).parallel().anyMatch(num -> {
            try {
                return !isPrime(num);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
