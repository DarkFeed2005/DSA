import java.util.LinkedList;
import java.util.Queue;

/**
 * Stack implemented using two queues - POP FRIENDLY.
 * push : O(n)  (enqueue new item into empty q2, move all of q1 behind it, swap)
 * pop  : O(1)  (front of q1 is always the top of the stack)
 */
public class QueueStackPopFriendly {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<Integer> temp = q1; q1 = q2; q2 = temp;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return q1.remove();
    }

    public int top() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return q1.peek();
    }

    public boolean isEmpty() { return q1.isEmpty(); }
    public int size() { return q1.size(); }

    public static void main(String[] args) {
        QueueStackPopFriendly s = new QueueStackPopFriendly();
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
