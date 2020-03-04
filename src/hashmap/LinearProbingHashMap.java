package hashmap;

public class LinearProbingHashMap<Key, Value> {
    private int capacity;
    private int size = 0;

    private Key[] keys;
    private Value[] values;

    private final Object DELETED = new Object();

    public LinearProbingHashMap(int capacity) {
        this.capacity = capacity;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        if (size == capacity - 1) throw new RuntimeException("No enough capacity");
        int hashIndex = hash(key);
        int delIndex = -1;
        for (int i = 0; i < capacity; i++) {
            if (keys[hashIndex] == null) break;
            if (delIndex == -1 && keys[hashIndex].equals(DELETED)) delIndex = hashIndex;
            else if (key.equals(keys[hashIndex])) {
                values[hashIndex] = value;
                return;
            }
            hashIndex = (hashIndex + 1) % capacity;
        }
        if (delIndex >= 0) hashIndex = delIndex;
        keys[hashIndex] = key;
        values[hashIndex] = value;
        size++;
    }

    public Value get(Key key) {
        isKeyNotNull(key);
        int hashIndex = hash(key);
        for (int i = 0; i < capacity; i++) {
            if (keys[hashIndex] == null) break;
            else if (key.equals(keys[hashIndex])) return values[hashIndex];
            hashIndex = (hashIndex + 1) % capacity;
        }
        return null;
    }

    public void remove(Key key) {
        isKeyNotNull(key);
        int hashIndex = hash(key);
        for (int i = 0; i < capacity; i++) {
            if (keys[hashIndex] == null) break;
            if (key.equals(keys[i])) {
                keys[i] = (Key) DELETED;
                size--;
                return;
            }
        }
    }

    private void isKeyNotNull(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
    }

    public int size() {
        return size;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public final int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public final int hash2(Key key) {
        return 7 - (key.hashCode() & 0x7fffffff) % 7;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (DELETED.equals(keys[i])) sb.append("DEL");
            else sb.append(keys[i]);
            if (i < capacity - 1) sb.append(", ");
        }
        return sb.toString();
    }

}
