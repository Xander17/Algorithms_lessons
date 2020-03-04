package arraylist;

import java.util.Arrays;

public class MyArrayList<T extends Comparable> {
    private final int DEFAULT_CAPACITY = 10;
    private final float CAPACITY_INCREASE_FACTOR = 1.5f;

    private T[] array;
    private int size;

    public MyArrayList(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity should be 1 or more");
        array = getNewArray(capacity);
    }

    public MyArrayList() {
        array = getNewArray(DEFAULT_CAPACITY);
    }

    private T[] getNewArray(int capacity) {
        return (T[]) (new Comparable[capacity]);
    }

    public void add(T element) {
        checkCapacity();
        insertElement(size, element);
    }

    public void add(int index, T element) {
        checkCapacity();
        checkIndex(index);
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        insertElement(index, element);
    }

    private void insertElement(int index, T element) {
        array[index] = element;
        size++;
    }

    private void checkCapacity() {
        if (size == array.length) increaseCapacity();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index " + index + " is out of range");
    }

    private void increaseCapacity() {
        array = Arrays.copyOf(array, (int) (array.length * CAPACITY_INCREASE_FACTOR));
    }

    public void remove(int index) {
        checkIndex(index);
        removeElement(index);
    }

    public boolean remove(T element) {
        int index = indexOf(element);
        if (index == -1) return false;
        removeElement(index);
        return true;
    }

    private void removeElement(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) return i;
        }
        return -1;
    }

    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    public void set(int index, T element) {
        checkIndex(index);
        array[index] = element;
    }

    public void bubbleSort() {
        boolean swapped;
        for (int i = size - 1; i > 0; i--) {
            swapped = false;
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public void selectionSort() {
        int minIndex;
        for (int i = 0; i < size - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) minIndex = j;
            }
            swap(i, minIndex);
        }
    }

    public void insertionSort() {
        T key;
        int j;
        for (int i = 1; i < size; i++) {
            j = i;
            key = array[i];
            while (j > 0 && key.compareTo(array[j - 1]) < 0) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = key;
        }
    }

    private void swap(int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[ ");
        for (int i = 0; i < size - 1; i++) {
            s.append(array[i]).append(", ");
        }
        s.append(array[size - 1]).append(" ]");
        return s.toString();
    }
}
