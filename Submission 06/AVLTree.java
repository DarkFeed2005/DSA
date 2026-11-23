import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AVL Tree - a self-balancing Binary Search Tree.
 * After every insert and delete, each node's balance factor
 *      balance = height(left subtree) - height(right subtree)
 * is kept in {-1, 0, 1}. When it reaches +2 or -2 the tree is repaired with rotations:
 *   - Left-Left  case  (balance > 1 and left child leans left or is even)  -> right rotation
 *   - Right-Right case (balance < -1 and right child leans right or is even) -> left rotation
 *   - Left-Right case  (balance > 1 and left child leans right)  -> left rotation on the child, then right rotation
 *   - Right-Left case  (balance < -1 and right child leans left) -> right rotation on the child, then left rotation
 *
 * Because the height is always O(log n), search, insert and delete are O(log n) in the
 * worst case (a plain BST can degrade to O(n)).
 * Height convention: an empty tree has height -1 and a single node has height 0.
 */
public class AVLTree {
    private static class Node {
        int key;
        int height;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Node root;
    private int size;
    private int rotations;

    // ---------- helpers ----------
    private int height(Node node) { return node == null ? -1 : node.height; }

    private void update(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        update(y);
        update(x);
        rotations++;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        update(x);
        update(y);
        rotations++;
        return y;
    }

    private Node rebalance(Node node) {
        update(node);
        int balance = balanceFactor(node);
        if (balance > 1) {
            if (balanceFactor(node.left) < 0) node.left = rotateLeft(node.left); // Left-Right
            return rotateRight(node);                                            // Left-Left
        }
        if (balance < -1) {
            if (balanceFactor(node.right) > 0) node.right = rotateRight(node.right); // Right-Left
            return rotateLeft(node);                                                 // Right-Right
        }
        return node;
    }

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
        return rebalance(node);
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
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = minNode(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }
        return rebalance(node);
    }

    // ---------- measurements ----------
    public int height() { return height(root); }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int rotationCount() { return rotations; }

    /** Verifies that every node has a correct stored height and a balance factor in {-1, 0, 1}. */
    public boolean isBalanced() { return check(root) != -2; }

    private int check(Node node) {
        if (node == null) return -1;
        int lh = check(node.left);
        if (lh == -2) return -2;
        int rh = check(node.right);
        if (rh == -2) return -2;
        if (Math.abs(lh - rh) > 1) return -2;
        int h = 1 + Math.max(lh, rh);
        return (node.height == h) ? h : -2;
    }

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
        // Each of the four rotation cases, using three keys
        String[] names = {"Right-Right (left rotation)", "Left-Left (right rotation)",
                          "Right-Left (double rotation)", "Left-Right (double rotation)"};
        int[][] cases = {{10, 20, 30}, {30, 20, 10}, {10, 30, 20}, {30, 10, 20}};
        for (int i = 0; i < cases.length; i++) {
            AVLTree t = new AVLTree();
            for (int key : cases[i]) t.insert(key);
            System.out.println("Insert " + java.util.Arrays.toString(cases[i]) + " -> " + names[i]
                    + ": preorder " + t.preorder() + ", rotations: " + t.rotationCount());
        }

        System.out.println();
        AVLTree avl = new AVLTree();
        for (int i = 1; i <= 7; i++) avl.insert(i);
        System.out.println("Inserting 1..7 in sorted order (a plain BST would have height 6):");
        avl.printTree();
        System.out.println("Height: " + avl.height() + ", balanced: " + avl.isBalanced()
                + ", rotations so far: " + avl.rotationCount());

        System.out.println();
        System.out.println("Inorder   : " + avl.inorder());
        System.out.println("Preorder  : " + avl.preorder());
        System.out.println("Postorder : " + avl.postorder());
        System.out.println("Levelorder: " + avl.levelOrder());

        System.out.println();
        avl.delete(4); // the root, which has two children
        avl.delete(1);
        avl.delete(2);
        System.out.println("After deleting 4, 1 and 2: " + avl.levelOrder()
                + " (levelorder), balanced: " + avl.isBalanced());

        System.out.println();
        AVLTree big = new AVLTree();
        for (int i = 1; i <= 1000; i++) big.insert(i);
        System.out.println("1000 keys inserted in sorted order -> height: " + big.height()
                + ", balanced: " + big.isBalanced() + " (a plain BST would have height 999)");
    }
}
