/**
 * Tower of Hanoi.
 *
 * Move n disks from peg A to peg C using peg B as a helper, obeying two rules:
 *   - only one disk may be moved at a time
 *   - a larger disk may never be placed on top of a smaller one
 *
 * Idea (recursion): to move n disks from -> to,
 *   1. move the top n-1 disks from -> helper   (using 'to' as the helper)
 *   2. move the largest disk from -> to
 *   3. move the n-1 disks helper -> to         (using 'from' as the helper)
 *
 * Minimum number of moves = 2^n - 1.  Time: O(2^n).  Recursion depth / space: O(n).
 */
public class HanoiTower {
    private static long moveCount;

    /** Solves the puzzle, optionally printing every move, and returns the number of moves. */
    public static long solve(int n, char from, char to, char helper, boolean print) {
        if (n < 0) throw new IllegalArgumentException("Number of disks cannot be negative");
        moveCount = 0;
        move(n, from, to, helper, print);
        return moveCount;
    }

    private static void move(int n, char from, char to, char helper, boolean print) {
        if (n == 0) return;
        move(n - 1, from, helper, to, print);
        moveCount++;
        if (print) {
            System.out.println("Move " + moveCount + ": disk " + n + " from " + from + " to " + to);
        }
        move(n - 1, helper, to, from, print);
    }

    public static void main(String[] args) {
        System.out.println("Solving Tower of Hanoi with 3 disks (A -> C using B):");
        long moves = solve(3, 'A', 'C', 'B', true);
        System.out.println("Total moves: " + moves);

        System.out.println();
        System.out.printf("%-8s %-12s %-12s%n", "Disks", "Moves", "2^n - 1");
        for (int n = 1; n <= 10; n++) {
            System.out.printf("%-8d %-12d %-12d%n", n, solve(n, 'A', 'C', 'B', false), (1L << n) - 1);
        }
    }
}
