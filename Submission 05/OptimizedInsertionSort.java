import java.util.Arrays;
import java.util.Random;

/**
 * Optimized Insertion Sort (binary insertion sort).
 * Two improvements over the basic version:
 *   1. Quick skip: if the next item is not smaller than the last sorted item, it is
 *      already in place - no searching or shifting (keeps the best case at O(n)).
 *   2. Binary search: instead of scanning the sorted section one item at a time, binary
 *      search finds the insertion position in O(log i) comparisons.
 * Ties go to the right of equal items, so the sort stays stable.
 *
 * Comparisons: O(n log n)   Shifts: still O(n^2) in the worst case (items must move)
 * Time: best O(n), average and worst O(n^2)    Space: O(1)    Stable: yes
 */
public class OptimizedInsertionSort {
    public static long comparisons;
    public static long shifts;

    public static void sort(int[] arr) {
        comparisons = 0;
        shifts = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];

            comparisons++;
            if (key >= arr[i - 1]) continue; // already in the right place

            // binary search in arr[0..i) for the first item greater than key
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                comparisons++;
                if (key < arr[mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            // shift arr[lo..i-1] one place to the right and insert the key
            for (int j = i; j > lo; j--) {
                arr[j] = arr[j - 1];
                shifts++;
            }
            arr[lo] = key;
        }
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(data));
        sort(data);
        System.out.println("After : " + Arrays.toString(data));
        System.out.println("Comparisons: " + comparisons + ", shifts: " + shifts);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        sort(sorted);
        System.out.println("Already sorted array -> comparisons: " + comparisons + " (just n-1), shifts: " + shifts);

        Random random = new Random(42);
        int[] big = new int[1000];
        for (int i = 0; i < big.length; i++) big[i] = random.nextInt(10000);
        int[] copy = big.clone();
        sort(big);
        Arrays.sort(copy);
        System.out.println("1000 random numbers sorted correctly: " + Arrays.equals(big, copy));
    }
}
