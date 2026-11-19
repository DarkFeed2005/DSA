import java.util.Stack;

/**
 * Queue implemented using two stacks - DEQUEUE FRIENDLY.
 * enqueue : O(n)  (move s1 -> s2, push new item to s1, move s2 -> s1 back)
 * dequeue : O(1)  (the front of the queue is always on top of s1)
 */
public class StackQueueDequeueFriendly {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void enqueue(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s1.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return s1.pop();
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return s1.peek();
    }

    public boolean isEmpty() { return s1.isEmpty(); }
    public int size() { return s1.size(); }

    public static void main(String[] args) {
        StackQueueDequeueFriendly q = new StackQueueDequeueFriendly();
        q.enqueue(10); q.enqueue(20); q.enqueue(30);
        System.out.println("Peek: " + q.peek());       // 10
        System.out.println("Dequeue: " + q.dequeue()); // 10
        System.out.println("Dequeue: " + q.dequeue()); // 20
        q.enqueue(40);
        System.out.println("Dequeue: " + q.dequeue()); // 30
        System.out.println("Dequeue: " + q.dequeue()); // 40
        System.out.println("Empty? " + q.isEmpty());   // true
    }
}
