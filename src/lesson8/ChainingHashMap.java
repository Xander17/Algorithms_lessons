package lesson8;

import java.util.LinkedList;

public class ChainingHashMap<Key, Value> {
    private int capacity;
    private int size = 0;

    private LinkedList<Node>[] nodes;

    public ChainingHashMap(int capacity) {
        this.capacity = capacity;
        nodes = new LinkedList[capacity];
        for (int i = 0; i < nodes.length; i++) nodes[i] = new LinkedList<>();
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        int hash = hash(key);
        for (Node node : nodes[hash]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        nodes[hash].addLast(new Node(key, value));
        size++;
    }

    public Value get(Key key) {
        isKeyNotNull(key);
        int hash = hash(key);
        for (Node node : nodes[hash]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    public void remove(Key key) {
        isKeyNotNull(key);
        int hash = hash(key);
        for (Node node : nodes[hash]) {
            if (key.equals(node.key)) {
                nodes[hash].remove(node);
                size--;
                return;
            }
        }
    }

    private void isKeyNotNull(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public final int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < nodes[i].size(); j++) {
                sb.append(nodes[i].get(j).key);
                if (j < nodes[i].size() - 1) sb.append(", ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private class Node {
        Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
