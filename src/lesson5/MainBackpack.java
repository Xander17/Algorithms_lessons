package lesson5;

import java.util.ArrayList;

public class MainBackpack {

    private static Item[] items;
    private static int maxWeight = 15;

    public static void main(String[] args) {
        items = new Item[20];

        for (int i = 0; i < items.length; i++) {
            items[i] = new Item("Item" + (i + 1), (int) (Math.random() * 9) + 1, (int) (Math.random() * 9) + 1);
        }

        System.out.println("All items:");
        itemsInfoPrint(items);
        System.out.println();
        System.out.println("Backpacked items by binary:");
        itemsInfoPrint(backpackFill());
        System.out.println();
        System.out.println("Backpacked items by recursive:");
        itemsInfoPrint(recursiveBackpackFill());
    }

    private static int maxPrice;
    private static int maxPriceWeight;
    private static int indexesMaxPrice;

    private static Item[] recursiveBackpackFill() {
        maxPrice = 0;
        maxPriceWeight = 0;
        indexesMaxPrice = 0;
        recursiveBackpackFill(0, 0, 0, 0);
        return putItemsByIndexes(getIndexes(indexesMaxPrice));
    }

    private static void recursiveBackpackFill(int startIndex, int binaryIndex, int sumPrice, int sumWeight) {
        if (startIndex >= items.length) {
            checkMax(sumPrice, sumWeight, binaryIndex);
            return;
        }
        for (int i = startIndex; i <= items.length; i++) {
            if (sumWeight + items[i].weight > maxWeight) {
                checkMax(sumPrice, sumWeight, binaryIndex);
                continue;
            }
            recursiveBackpackFill(i + 1, binaryIndex + (int) Math.pow(2, i), sumPrice + items[i].price, sumWeight + items[i].weight);
        }
    }

    private static void checkMax(int sumPrice, int sumWeight, int binaryIndex) {
        if (sumPrice > maxPrice || (sumPrice == maxPrice && sumWeight < maxPriceWeight)) {
            maxPrice = sumPrice;
            maxPriceWeight = sumWeight;
            indexesMaxPrice = binaryIndex;
        }
    }

    private static Item[] backpackFill() {
        int len = (int) Math.pow(2, items.length);
        int maxPrice = 0;
        int maxPriceWeight = 0;
        Integer[] indexesOfMax = new Integer[0];
        for (int i = 1; i < len; i++) {
            Integer[] indexes = getIndexes(i);
            int sumWeight = 0;
            int sumPrice = 0;
            for (int j = 0; j < indexes.length; j++) {
                if (indexes[j] == 1) {
                    sumWeight += items[j].weight;
                    if (sumWeight > maxWeight) break;
                    sumPrice += items[j].price;
                }
            }
            if (sumWeight <= maxWeight &&
                    ((sumPrice > maxPrice) || (sumPrice == maxPrice && sumWeight < maxPriceWeight))) {
                maxPrice = sumPrice;
                maxPriceWeight = sumWeight;
                indexesOfMax = indexes;
            }
        }
        return putItemsByIndexes(indexesOfMax);
    }

    private static Integer[] getIndexes(int binaryIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        while (binaryIndex > 0) {
            list.add(binaryIndex % 2);
            binaryIndex /= 2;
        }
        return list.toArray(new Integer[0]);
    }

    private static Item[] putItemsByIndexes(Integer[] indexes) {
        ArrayList<Item> itemsInBackpack = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] == 1) itemsInBackpack.add(items[i]);
        }
        return itemsInBackpack.toArray(new Item[0]);
    }

    private static void itemsInfoPrint(Item[] items) {
        int sumWeight = 0, sumPrice = 0;
        for (Item item : items) {
            System.out.println(item);
            sumWeight += item.weight;
            sumPrice += item.price;
        }
        System.out.println("Weight: " + sumWeight + "; Price: " + sumPrice);
    }

    private static class Item {
        private String name;
        private int weight;
        private int price;

        Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + ": weight=" + weight + ", price=" + price;
        }
    }
}
