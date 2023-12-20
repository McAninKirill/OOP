package org.example;

public class HeapSort {
    public static int[] heapSort(int[] inputArr)
    {
        int n = inputArr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(inputArr, n, i);
        }

        for (int i=n-1; i>=0; i--)
        {

            int temp = inputArr[0];
            inputArr[0] = inputArr[i];
            inputArr[i] = temp;

            heapify(inputArr, i, 0);
        }
        return inputArr;
    }


    private static void heapify(int[] arr, int size, int root)
    {
        int largest = root;
        int left = 2*root + 1;
        int right = 2*root + 2;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != root)
        {
            int swap = arr[root];
            arr[root] = arr[largest];
            arr[largest] = swap;

            heapify(arr, size, largest);
        }
    }
}