package arraylist;

import java.util.Random;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        int size = 1000000;
        int range = 1000000;

        MyArrayList<Integer> list1 = new MyArrayList<>(size);
        MyArrayList<Integer> list2 = new MyArrayList<>(size);
        MyArrayList<Integer> list3 = new MyArrayList<>(size);

        for (int i = 0; i < size; i++) {
            int a = random.nextInt(range);
            list1.add(a);
            list2.add(a);
            list3.add(a);
        }
        System.out.println("Bubble sort:\t" + checkMethodTime(list1::bubbleSort) + "ms");
        System.out.println("Selection sort:\t" + checkMethodTime(list2::selectionSort) + "ms");
        System.out.println("Insertion sort:\t" + checkMethodTime(list3::insertionSort) + "ms");
    }

    private static long checkMethodTime(Runnable method) {
        long start = System.currentTimeMillis();
        method.run();
        return System.currentTimeMillis() - start;
    }
}
