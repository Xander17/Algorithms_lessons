package linkedlist;

public class MyLinkedQueue<T> {
    private MyLinkedList<T> list;

    public MyLinkedQueue() {
        list = new MyLinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public void add(T element) {
        list.insertLast(element);
    }

    public T remove() {
        return list.removeFirst();
    }

    public T peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
