package org.example.hasnotprimecheck;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.example.hasnotprimecheck.ConsistentlyPrime.consistently;
import static org.example.hasnotprimecheck.ThreadPrime.parallelThread;
import static org.example.hasnotprimecheck.StreamPrime.parallelStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты.
 */
public class NonPrimeTest {

    /**
     * Простой тест для проверки работоспособности последовательного способа.
     */
    @Test
    void simpleCons() throws Exception {
        int[] arr = {17, 13, 4, 5, 7};
        assertTrue(consistently(arr));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Threads.
     */
    @Test
    void simpleThr() throws Exception {
        int[] arr = {17, 13, 4, 7, 5};
        assertTrue(parallelThread(arr, 2));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Stream.
     */
    @Test
    void simpleStr() {
        int[] arr = {17, 13, 4, 7, 5};
        assertTrue(parallelStream(arr));
    }

    /**
     * Простой тест для проверки единицы для последовательного способа.
     */
    @Test
    void oneCons()  {
        int[] arr = {17, 13, 1, 5, 7};
        assertTrue(consistently(arr));
    }

    /**
     * Простой тест для проверки единицы для параллельного способа с Threads
     */
    @Test
    void oneThr(){
        int[] arr = {17, 13, 1, 7, 5};
        assertTrue(parallelThread(arr, 2));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Stream
     */
    @Test
    void oneStr() throws Exception {
        int[] arr = {17, 13, 1, 7, 5};
        assertTrue(parallelStream(arr));
    }

}