package linkedlist;

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.insertFirst(11);
        list.insertFirst(22);
        list.insertFirst(33);
        list.insertFirst(44);
        list.insertFirst(55);
        System.out.println(list);
        ListIterator<Integer> iterator = list.listIterator();

        while (iterator.hasNext()) {
            iterator.add(66);
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
        System.out.println();
        System.out.println(list);

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
            iterator.remove();
        }
        System.out.println();
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
        System.out.println();
        System.out.println(list);
    }
}
