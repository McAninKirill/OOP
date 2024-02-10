package org.example;

import java.util.Arrays;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.example.NotPrime.consistently;
import static org.example.NotPrime.parallelThread;
import static org.example.NotPrime.parallelStream;

/**
 * Тесты
 */
public class NonPrimeTest {

    /**
     * Простой тест для проверки работоспособности последовательного способа
     */
    @Test
    void simpleCons() throws Exception {
        int[] arr = {17, 13, 1, 5, 7};
        assertEquals(true, consistently(arr));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Threads
     */
    @Test
    void simpleThr() throws Exception {
        int[] arr = {17, 13, 1, 7, 5};
        assertEquals(true, parallelThread(arr, 2));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Stream
     */
    @Test
    void simpleStr() throws Exception {
        int[] arr = {17, 13, 1, 7, 5};
        assertEquals(true, parallelStream(arr));
    }


    /**
     * Большой тест для проверки скорости последовательного способа
     */
    @Test
    void hardCons() throws Exception {
        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        long start = System.nanoTime();
        boolean ans = consistently(arr);
        long end = System.nanoTime();

        assertEquals(false, ans);

        System.out.println("Consistently: " + Duration.ofNanos(end - start).toMillis() + " ms");
    }

    /**
     * Большой тест для проверки скорости параллельного способа с Threads
     */
    @Test
    void hardThr() throws Exception {
        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        long start = System.nanoTime();
        boolean ans = parallelThread(arr, 4);
        long end = System.nanoTime();

        assertEquals(false, ans);

        System.out.println("Threads: " + Duration.ofNanos(end - start).toMillis() + " ms");
    }

    /**
     * Большой тест для проверки скорости параллельного способа с Stream
     */
    @Test
    void hardStr() throws Exception {
        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        long start = System.nanoTime();
        boolean ans = parallelStream(arr);
        long end = System.nanoTime();

        assertEquals(false, ans);

        System.out.println("Stream: " + Duration.ofNanos(end - start).toMillis() + " ms");
    }
}