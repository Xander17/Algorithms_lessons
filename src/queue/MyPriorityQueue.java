package queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;

public class MyPriorityQueue<T extends Comparator<T>> {
    private T[] array;
    private int size = 0;
    private Comparator<T> comparator;
    private final int DEFAULT_CAPACITY = 10;
    private final float CAPACITY_INCREASE_FACTOR = 1.5f;

    public MyPriorityQueue(int capacity, Comparator<T> comparator) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity should be 1 or more");
        this.comparator = comparator;
        array = getNewArray(capacity);
    }

    public MyPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
        array = getNewArray(DEFAULT_CAPACITY);
    }

    private T[] getNewArray(int capacity) {
        return (T[]) (new Object[capacity]);
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity() {
        array = Arrays.copyOf(array, (int) (array.length * CAPACITY_INCREASE_FACTOR));
    }

    public void add(T element) {
        if (isFull()) increaseCapacity();
        array[size] = element;
        size++;
        sort();
    }

    private void sort() {
        int i = size - 1;
        while (i > 0 && comparator.compare(array[i], array[i - 1]) > 0) {
            swap(i, i - 1);
            i--;
        }
    }

    public T remove() {
        T element = peek();
        size--;
        array[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return array[size - 1];
    }

    private void swap(int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("---{").append(System.lineSeparator());
        for (int i = size - 1; i >= 0; i--) {
            s.append("\t").append(array[i]).append(System.lineSeparator());
        }
        s.append("}---");
        return s.toString();
    }
}
