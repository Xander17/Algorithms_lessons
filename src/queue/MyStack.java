package queue;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] array;
    private int size=0;
    private final int DEFAULT_CAPACITY = 10;
    private final float CAPACITY_INCREASE_FACTOR = 1.5f;

    public MyStack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity should be 1 or more");
        array = getNewArray(capacity);
    }

    public MyStack() {
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

    public void push(T element) {
        if (isFull()) increaseCapacity();
        array[size] = element;
        size++;
    }

    public T pop() {
        T element = peek();
        size--;
        array[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return array[size - 1];
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
