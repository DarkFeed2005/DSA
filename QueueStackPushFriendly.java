import java.util.LinkedList;
import java.util.Queue;

/**
 * Stack implemented using two queues - PUSH FRIENDLY.
 * push : O(1)  (just enqueue into q1)
 * pop  : O(n)  (move n-1 items to q2, dequeue the last one, swap queues)
 */
public class QueueStackPushFriendly {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void push(int x) {
        q1.add(x);
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        while (q1.size() > 1) {
            q2.add(q1.remove());
        }
        int top = q1.remove();
        Queue<Integer> temp = q1; q1 = q2; q2 = temp;
        return top;
    }

    public int top() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        while (q1.size() > 1) {
            q2.add(q1.remove());
        }
        int top = q1.remove();
        q2.add(top);
        Queue<Integer> temp = q1; q1 = q2; q2 = temp;
        return top;
    }

    public boolean isEmpty() { return q1.isEmpty(); }
    public int size() { return q1.size(); }

    public static void main(String[] args) {
        QueueStackPushFriendly s = new QueueStackPushFriendly();
        s.push(10); s.push(20); s.push(30);
        System.out.println("Top: " + s.top());   // 30
        System.out.println("Pop: " + s.pop());   // 30
        System.out.println("Pop: " + s.pop());   // 20
        s.push(40);
        System.out.println("Pop: " + s.pop());   // 40
        System.out.println("Pop: " + s.pop());   // 10
        System.out.println("Empty? " + s.isEmpty()); // true
    }
}
