package hashmap;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        ChainingHashMap<Integer, String> chainMap = new ChainingHashMap<>(20);
        for (int i = 0; i < 100; i++) {
            chainMap.put(i, "Value" + i);
        }
        System.out.println(chainMap);
        System.out.println();
        Random random = new Random();
        for (int i = 0; i < 100; i += random.nextInt(10)) {
            chainMap.remove(i);
        }
        System.out.println(chainMap);
        System.out.println();

        LinearProbingHashMap<Integer, String> linearMap = new LinearProbingHashMap<>(21);
        for (int a = 0, i = 0; i < 20; i++, a += 5) {
            linearMap.put(a, "Value" + i);
        }
        System.out.println(linearMap);
        linearMap.remove(85);
        System.out.println(linearMap);
        linearMap.put(23,"newVal");
        System.out.println(linearMap);
        linearMap.remove(65);
        System.out.println(linearMap);
        linearMap.remove(22);
        System.out.println(linearMap.get(95));
        linearMap.put(64,"Val65");
        System.out.println(linearMap);
        System.out.println(linearMap.get(64));
    }
}
