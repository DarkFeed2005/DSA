import java.util.Stack;

/**
 * Queue implemented using two stacks - ENQUEUE FRIENDLY.
 * enqueue : O(1)  (just push onto s1)
 * dequeue : O(n)  (move everything to s2, pop the bottom item, move back)
 */
public class StackQueueEnqueueFriendly {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void enqueue(int x) {
        s1.push(x);
    }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int front = s2.pop();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return front;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int front = s2.peek();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return front;
    }

    public boolean isEmpty() { return s1.isEmpty(); }
    public int size() { return s1.size(); }

    public static void main(String[] args) {
        StackQueueEnqueueFriendly q = new StackQueueEnqueueFriendly();
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
