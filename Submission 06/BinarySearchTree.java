import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary Search Tree (BST) of ints.
 * For every node, all keys in its left subtree are smaller and all keys in its right
 * subtree are larger. Duplicate keys are ignored.
 *
 * Search, insert, delete: O(h) where h is the height of the tree
 *   - balanced shape (random data): h is about log n, so O(log n)
 *   - degenerate shape (sorted data): h = n - 1, so O(n)  <- the problem AVL trees solve
 * Height convention: an empty tree has height -1 and a single node has height 0.
 */
public class BinarySearchTree {
    private static class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Node root;
    private int size;

    // ---------- insert ----------
    public boolean insert(int key) {
        if (contains(key)) return false; // ignore duplicates
        root = insert(root, key);
        size++;
        return true;
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else node.right = insert(node.right, key);
        return node;
    }

    // ---------- search ----------
    public boolean contains(int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) return true;
            cur = (key < cur.key) ? cur.left : cur.right;
        }
        return false;
    }

    public int min() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return minNode(root).key;
    }

    public int max() {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    private Node minNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // ---------- delete ----------
    public boolean delete(int key) {
        if (!contains(key)) return false;
        root = delete(root, key);
        size--;
        return true;
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // case 1 and 2: no child or one child -> replace the node with its child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // case 3: two children -> copy the in-order successor, then delete it
            Node successor = minNode(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }
        return node;
    }

    // ---------- measurements ----------
    public int height() { return height(root); }

    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    // ---------- traversals ----------
    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Integer> out) {
        if (node == null) return;
        inorder(node.left, out);
        out.add(node.key);
        inorder(node.right, out);
    }

    public List<Integer> preorder() {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.key);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    public List<Integer> postorder() {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node node, List<Integer> out) {
        if (node == null) return;
        postorder(node.left, out);
        postorder(node.right, out);
        out.add(node.key);
    }

    public List<Integer> levelOrder() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            result.add(node.key);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }

    // ---------- display ----------
    /** Prints the tree sideways: the right subtree is on top, the root is on the left. */
    public void printTree() {
        if (root == null) { System.out.println("(empty)"); return; }
        printTree(root, 0);
    }

    private void printTree(Node node, int depth) {
        if (node == null) return;
        printTree(node.right, depth + 1);
        System.out.println("    ".repeat(depth) + node.key);
        printTree(node.left, depth + 1);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int key : new int[]{50, 30, 70, 20, 40, 60, 80}) bst.insert(key);

        System.out.println("Tree (sideways, root on the left):");
        bst.printTree();
        System.out.println("Inorder   : " + bst.inorder() + "  (sorted)");
        System.out.println("Preorder  : " + bst.preorder());
        System.out.println("Postorder : " + bst.postorder());
        System.out.println("Levelorder: " + bst.levelOrder());
        System.out.println("Size: " + bst.size() + ", height: " + bst.height()
                + ", min: " + bst.min() + ", max: " + bst.max());
        System.out.println("contains(60): " + bst.contains(60) + ", contains(65): " + bst.contains(65));

        System.out.println();
        bst.delete(20); // leaf
        System.out.println("After deleting 20 (leaf)        : " + bst.inorder());
        bst.delete(30); // one child
        System.out.println("After deleting 30 (one child)   : " + bst.inorder());
        bst.delete(50); // two children (the root)
        System.out.println("After deleting 50 (two children): " + bst.inorder());
        System.out.println("Levelorder now: " + bst.levelOrder());

        System.out.println();
        BinarySearchTree skewed = new BinarySearchTree();
        for (int i = 1; i <= 7; i++) skewed.insert(i);
        System.out.println("Inserting 1..7 in sorted order gives height " + skewed.height()
                + " (a linked list!), a balanced tree would have height 2.");
    }
}
