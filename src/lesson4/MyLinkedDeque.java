package lesson4;

public class MyLinkedDeque<T> {
    private MyLinkedList<T> list;

    public MyLinkedDeque() {
        list = new MyLinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public void addFirst(T element) {
        list.insertFirst(element);
    }

    public void addLast(T element) {
        list.insertLast(element);
    }

    public T removeFirst() {
        return list.removeFirst();
    }

    public T removeLast() {
        return list.removeLast();
    }

    public T peekFirst() {
        return list.getFirst();
    }

    public T peekLast() {
        return list.getLast();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
