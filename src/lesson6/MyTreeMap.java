package lesson6;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;
        private int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
            height = 0;
        }

        @Override
        public String toString() {
            return "[" + key + " = " + value + "]";
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void checkKeyIsNotNull(Key key) {
        if (key == null) throw new NullPointerException("Key should not be a null");
    }

    public final boolean contains(Key key) {
        return get(key) != null;
    }

    public final Value get(Key key) {
        checkKeyIsNotNull(key);
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node.value;
        else if (cmp < 0) return get(node.left, key);
        else return get(node.right, key);
    }

    public final void put(Key key, Value value) {
        checkKeyIsNotNull(key);
        if (value == null) return;
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp == 0) node.value = value;
        else if (cmp < 0) node.left = put(node.left, key, value);
        else node.right = put(node.right, key, value);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public Key minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key maxKey() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    public void deleteMin() {
        if (isEmpty()) return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void deleteMax() {
        if (isEmpty()) return;
        root = deleteMin(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMin(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public final void delete(Key key) {
        checkKeyIsNotNull(key);
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node tmp = node;
            node = min(node.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) return true;
        return isBalanced(node.left) && isBalanced(node.right) && (Math.abs(height(node.left) - height(node.right)) <= 1);
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        return toString(node.left) + " " + node + " " + toString(node.right);
    }
}
