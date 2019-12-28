package lesson4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    private class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E value, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        Node(E value, Node<E> next) {
            this(null, value, next);
        }

        Node(Node<E> prev, E value) {
            this(prev, value, null);
        }
    }

    private class InnerIterator implements Iterator<T> {
        private Node<T> current = new Node<>(null, null, first);
        private boolean removeEnable = false;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            if (current.next == null) throw new NoSuchElementException();
            current = current.next;
            removeEnable = true;
            return current.value;
        }

        @Override
        public void remove() {
            if (removeEnable) {
                removeNode(current);
                removeEnable = false;
            } else throw new IllegalStateException();
        }
    }

    private class InnerListIterator implements ListIterator<T> {
        private Node<T> nextNode = first;
        private Node<T> current = null;
        private Node<T> prevNode = null;
        private int index = -1;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            index++;
            if (nextNode == null) throw new NoSuchElementException();
            T value = nextNode.value;
            current = nextNode;
            prevNode = nextNode;
            nextNode = nextNode.next;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return prevNode != null;
        }

        @Override
        public T previous() {
            index--;
            if (prevNode == null) throw new NoSuchElementException();
            T value = prevNode.value;
            current = prevNode;
            nextNode = prevNode;
            prevNode = prevNode.prev;
            return value;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index;
        }

        @Override
        public void set(T value) {
            if (current == null) throw new IllegalStateException();
            current.value = value;
        }

        @Override
        public void remove() {
            if (current == null) throw new IllegalStateException();
            removeNode(current);
            if (prevNode == current) prevNode = current.prev;
            if (nextNode == current) nextNode = current.next;
            current = null;
        }

        @Override
        public void add(T value) {
            if (index == -1) {
                insertFirst(value);
                prevNode = first;
            } else if (index == size - 1) {
                insertLast(value);
                prevNode = last;
            } else {
                insertBefore(value, nextNode);
                prevNode = nextNode.prev;
            }
            index++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    public ListIterator<T> listIterator() {
        return new InnerListIterator();
    }

    public void insertFirst(T value) {
        Node<T> newNode = new Node<>(value, first);
        if (isEmpty()) last = newNode;
        else first.prev = newNode;
        first = newNode;
        size++;
    }

    public void insertLast(T value) {
        Node<T> newNode = new Node<>(last, value);
        if (isEmpty()) first = newNode;
        else last.next = newNode;
        last = newNode;
        size++;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            insertFirst(value);
            return;
        }
        if (index == size) {
            insertLast(value);
            return;
        }
        insertBefore(value, getNode(index));
    }

    private void insertBefore(T value, Node<T> beforeNode) {
        Node<T> newNode = new Node<>(beforeNode.prev, value, beforeNode);
        beforeNode.prev.next = newNode;
        beforeNode.prev = newNode;
        size++;
    }

    private void insertAfter(Node<T> afterNode, T value) {
        Node<T> newNode = new Node<>(afterNode, value, afterNode.next);
        afterNode.next.prev = newNode;
        afterNode.next = newNode;
        size++;
    }

    public T removeFirst() {
        checkEmptyException();
        return removeFirstNode();
    }

    private T removeFirstNode() {
        Node<T> node = first;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        else first.prev = null;
        return node.value;
    }

    public T removeLast() {
        checkEmptyException();
        return removeLastNode();
    }

    private T removeLastNode() {
        Node<T> node = last;
        last = last.prev;
        size--;
        if (isEmpty()) first = null;
        else last.next = null;
        return node.value;
    }

    public boolean remove(T value) {
        if (isEmpty()) return false;
        Node<T> node = getNode(value);
        if (node == null) return false;
        removeNode(node);
        return true;
    }

    private void removeNode(Node<T> node) {
        if (node == first)
            removeFirstNode();
        else if (node == last)
            removeLastNode();
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    public T getFirst() {
        checkEmptyException();
        return first.value;
    }

    public T getLast() {
        checkEmptyException();
        return last.value;
    }

    public int indexOf(T value) {
        return getIndex(value);
    }

    private int getIndex(T value) {
        Node<T> current1 = first;
        Node<T> current2 = last;
        for (int i = 0; i < (size + 1) / 2; i++) {
            if (current1.value.equals(value)) return i;
            if (current2.value.equals(value)) return size - 1 - i;
            current1 = current1.next;
            current2 = current2.prev;
        }
        return -1;
    }

    private Node<T> getNode(T value) {
        Node<T> current = first;
        while (current != null) {
            if (current.value.equals(value)) return current;
            current = current.next;
        }
        return null;
    }

    private Node<T> getNode(int index) {
        Node<T> current = first;
        int i = 0;
        while (i < index && current != null) {
            current = current.next;
            i++;
        }
        return current;
    }

    public boolean contains(T value) {
        return getIndex(value) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkEmptyException() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[ ]";
        StringBuilder s = new StringBuilder("[ ");
        Node<T> current = first;
        while (current != null) {
            s.append(current.value);
            current = current.next;
            if (current != null) s.append(", ");
        }
        s.append(" ]");
        return s.toString();
    }
}
