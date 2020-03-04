package queue;

import java.util.EmptyStackException;

public class MyDeque<T> {
    private T[] array;
    private int size=0;
    private int beginIndex=0;
    private int endIndex=0;
    private final int DEFAULT_CAPACITY = 10;
    private final float CAPACITY_INCREASE_FACTOR = 1.5f;

    public MyDeque(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity should be 1 or more");
        array = getNewArray(capacity);
    }

    public MyDeque() {
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
        T[] newArray = getNewArray((int) (array.length * CAPACITY_INCREASE_FACTOR));
        for (int i = 0; i < size; i++) {
            newArray[i] = (array[newIndex(i + beginIndex)]);
        }
        array = newArray;
        beginIndex = 0;
        endIndex = size;
    }

    private int newIndex(int index) {
        if (index < 0) index += array.length;
        return index % array.length;
    }

    public void addFirst(T element) {
        if (isFull()) increaseCapacity();
        beginIndex = newIndex(beginIndex - 1);
        array[beginIndex] = element;
        size++;
    }

    public void addLast(T element) {
        if (isFull()) increaseCapacity();
        array[endIndex] = element;
        size++;
        endIndex = newIndex(endIndex + 1);
    }

    public T removeFirst() {
        T element = peekFirst();
        size--;
        array[beginIndex] = null;
        beginIndex = newIndex(beginIndex + 1);
        return element;
    }

    public T removeLast() {
        T element = peekLast();
        size--;
        array[endIndex] = null;
        endIndex = newIndex(endIndex - 1);
        return element;
    }

    public T peekFirst() {
        if (isEmpty()) throw new EmptyStackException();
        return array[beginIndex];
    }

    public T peekLast() {
        if (isEmpty()) throw new EmptyStackException();
        return array[newIndex(endIndex - 1)];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("---{").append(System.lineSeparator());
        for (int i = 0; i < size; i++) {
            s.append("\t").append(array[newIndex(i + beginIndex)]).append(System.lineSeparator());
        }
        s.append("}---");
        return s.toString();
    }
}
