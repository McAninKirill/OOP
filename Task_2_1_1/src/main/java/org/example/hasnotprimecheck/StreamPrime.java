package org.example.hasnotprimecheck;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class StreamPrime extends NotPrime{
    private final int numThreads;
    public StreamPrime(int numThreads){
        if (numThreads <= 0) {
            throw new IllegalArgumentException();
        }
        this.numThreads = numThreads;
    }
    /**
     * Метод для параллельного способа с применением Stream
     *
     * @param array - список, который нам надо проверить
     * @return true or else
     */
    public boolean hasPrime(int[] array) {
        ForkJoinPool threadPool = new ForkJoinPool(this.numThreads);
            return threadPool.submit(() ->
                    Arrays.stream(array)
                            .parallel()
                            .anyMatch(num -> !isPrime(num))
            ).join();
    }
}
