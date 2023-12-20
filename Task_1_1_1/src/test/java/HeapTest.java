import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

import static org.example.HeapSort.heapSort;
public class HeapTest {

    @Test
    void firstTest(){
        int arr[] = {17, 13, 1, -6, 6, 9};
        arr = heapSort(arr);
        assertArrayEquals(new int[] {-6, 1, 6, 9, 13, 17}, arr);
    }

    @Test
    void emptyTest(){
        int arr[] = {};
        arr = heapSort(arr);
        assertArrayEquals(new int[] {}, arr);
    }

    @Test
    void negativeTest(){
        int arr[] = {-17, -13, -1, -6, -5, -9};
        arr = heapSort(arr);
        assertArrayEquals(new int[] {-17, -13, -9, -6, -5, -1}, arr);
    }

    @Test
    void largeTest(){
        int randomNum;
        int [] arr = new int[1000];
        for(int i = 0; i < 1000; i++){
            randomNum = -1000 + (int)(Math.random() * 1000);
            arr[i] = randomNum;
        }
        int [] newArr = heapSort(arr);
        Arrays.sort(arr);
        assertArrayEquals(arr, newArr);
    }

}
