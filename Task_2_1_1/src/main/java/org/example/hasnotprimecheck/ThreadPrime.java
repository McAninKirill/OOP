package org.example.hasnotprimecheck;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPrime extends NotPrime{
    private static final AtomicBoolean hasNonPrime = new AtomicBoolean(false);

    /**
     * Лямбда функция для Thread.
     *
     * @param array - список, который нам надо проверить
     * @param start - индекс с какого элемента будем работать
     * @param end - индекс до какого элемента будем
     * @return - function
     */
    public static Runnable forThread(int[] array, int start, int end){
        return () -> {
            for (int j = start; j < end; j++) {
                try {
                    if (!isPrime(array[j])) {
                        hasNonPrime.set(true);
                    }
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    /**
     * Метод для параллельного способа с применением Threads.
     *
     * @param array - список, который нам надо проверить
     * @param numThreads - число потоков
     * @return - true or false
     */
    public static boolean parallelThread(int[] array, int numThreads){
        Thread[] threads = new Thread[numThreads];
        int chunkSize = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;
            threads[i] = new Thread(forThread(array, start, end));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return hasNonPrime.get();
    }

}
