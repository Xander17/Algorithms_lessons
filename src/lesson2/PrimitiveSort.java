package lesson2;

import java.util.Random;

public class PrimitiveSort {
    private int size;
    private int[] array1;
    private int[] array2;
    private int[] array3;
    private Random random = new Random();

    public static void main(String[] args) {
        new PrimitiveSort();
    }

    private PrimitiveSort() {
        size = 1000000;
        array1 = new int[size];
        array2 = new int[size];
        array3 = new int[size];
        for (int i = 0; i < size; i++) {
            int a = random.nextInt(10);
            array1[i] = a;
            array2[i] = a;
            array3[i] = a;
        }

        System.out.println("Bubble sort:\t" + checkMethodTime(() -> bubbleSort(array1)) + "ms");
        System.out.println("Selection sort:\t" + checkMethodTime(() -> selectionSort(array2)) + "ms");
        System.out.println("Insertion sort:\t" + checkMethodTime(() -> insertionSort(array3)) + "ms");
    }

    private long checkMethodTime(Runnable method) {
        long start = System.currentTimeMillis();
        method.run();
        return System.currentTimeMillis() - start;
    }

    private void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = size - 1; i > 0; i--) {
            swapped = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private void selectionSort(int[] array) {
        int minIndex;
        for (int i = 0; i < size - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[minIndex]) minIndex = j;
            }
            swap(array, i, minIndex);
        }
    }

    private void insertionSort(int[] array) {
        int key;
        int j;
        for (int i = 1; i < size; i++) {
            j = i;
            key = array[i];
            while (j > 0 && key < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = key;
        }
    }

    private void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
