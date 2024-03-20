package org.example.hasnotprimecheck;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты.
 */
public class HardTest {

    /**
     * Большой тест для проверки скорости последовательного способа
     */
    @Test
    void hardCons() {
        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        ConsistentlyPrime cons = new ConsistentlyPrime();
        long start = System.nanoTime();
        boolean ans = cons.hasPrime(arr);
        long end = System.nanoTime();

        assertFalse(ans);

        System.out.println("Consistently: " + Duration.ofNanos(end - start).toMillis() + " ms");
    }

    /**
     * Большой тест для проверки скорости параллельного способа с Threads
     */
    @Test
    void hardStr(){
        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        StreamPrime str = new StreamPrime(4);
        long start = System.nanoTime();
        boolean ans = str.hasPrime(arr);
        long end = System.nanoTime();

        assertFalse(ans);

        System.out.println("Threads: " + Duration.ofNanos(end - start).toMillis() + " ms");
    }

    /**
     * Большой тест для проверки скорости параллельного способа с Stream
     */
    @Test
    void hardThr(){
        int[] arr = new int[10000000];
        Arrays.fill(arr, 99971);
        ThreadPrime thr = new ThreadPrime(4);
        long start = System.nanoTime();
        boolean ans = thr.hasPrime(arr);
        long end = System.nanoTime();

        assertFalse(ans);

        System.out.println("Stream: " + Duration.ofNanos(end - start).toMillis() + " ms");
    }
}