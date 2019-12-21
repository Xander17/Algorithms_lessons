package lesson3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars;
        while ((chars = in.nextLine().toCharArray()).length != 0) {
            MyStack<Character> stack = new MyStack<>();
            for (int i = 0; i < chars.length; i++) {
                stack.push(chars[i]);
            }
            int size = stack.getSize();
            for (int i = 0; i < size; i++) {
                System.out.print(stack.pop());
            }
            System.out.println();
        }
    }
}
