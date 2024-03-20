package org.example.hasnotprimecheck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты.
 */
public class NonPrimeTest {

    /**
     * Простой тест для проверки работоспособности последовательного способа.
     */
    @Test
    void simpleCons(){
        int[] arr = {17, 13, 4, 5, 7};
        ConsistentlyPrime cons = new ConsistentlyPrime();
        assertTrue(cons.hasPrime(arr));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Threads.
     */
    @Test
    void simpleThr(){
        int[] arr = {17, 13, 4, 7, 5};
        ThreadPrime thr = new ThreadPrime(2);
        assertTrue(thr.hasPrime(arr));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Stream.
     */
    @Test
    void simpleStr() {
        int[] arr = {17, 13, 4, 7, 5};
        StreamPrime str = new StreamPrime(2);
        assertTrue(str.hasPrime(arr));
    }

    /**
     * Простой тест для проверки единицы для последовательного способа.
     */
    @Test
    void oneCons()  {
        int[] arr = {17, 13, 1, 5, 7};
        ConsistentlyPrime cons = new ConsistentlyPrime();
        assertTrue(cons.hasPrime(arr));
    }

    /**
     * Простой тест для проверки единицы для параллельного способа с Threads
     */
    @Test
    void oneThr(){
        int[] arr = {17, 13, 1, 7, 5};
        ThreadPrime thr = new ThreadPrime(2);
        assertTrue(thr.hasPrime(arr));
    }

    /**
     * Простой тест для проверки работоспособности параллельного способа с Stream
     */
    @Test
    void oneStr() throws Exception {
        int[] arr = {17, 13, 1, 7, 5};
        StreamPrime str = new StreamPrime(2);
        assertTrue(str.hasPrime(arr));
    }

}