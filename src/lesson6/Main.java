package lesson6;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int imbalanced = 0;
        //for (int i = 0; i < 20; i++) {
        while (true) {
            MyTreeMap<Integer, Integer> map = new MyTreeMap<>();
            while (map.height() < 6) {
                int key = 100 - random.nextInt(200);
                int value = 100 - random.nextInt(200);
                map.put(key, value);
            }
            System.out.println(map);
            boolean balanced = map.isBalanced();
            System.out.println("height: " + map.height() + ", balanced: " + balanced);
            if (balanced) break;
            imbalanced++;
        }
        System.out.println();
        System.out.println("Total maps created: " + (imbalanced + 1));
        System.out.println("Imbalanced maps: " + (imbalanced / (imbalanced + 1.) * 100) + "%");
    }
}
