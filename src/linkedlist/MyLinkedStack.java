package linkedlist;

public class MyLinkedStack<T> {
    private MyLinkedList<T> list;

    public MyLinkedStack() {
        list = new MyLinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public void push(T element) {
        list.insertLast(element);
    }

    public T pop() {
        return list.removeLast();
    }

    public T peek() {
        return list.getLast();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
