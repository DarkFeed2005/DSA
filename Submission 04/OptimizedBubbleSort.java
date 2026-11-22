import java.util.Arrays;

/**
 * Optimized Bubble Sort.
 * Two improvements over the basic version:
 *   1. Early exit: if a full pass makes no swap, the array is sorted, so stop.
 *   2. Shrinking range: everything after the position of the last swap is already in
 *      its final place, so the next pass only needs to go up to that position.
 *
 * Time: best O(n) (already sorted), average and worst O(n^2)    Space: O(1)    Stable: yes
 */
public class OptimizedBubbleSort {
    public static long comparisons;
    public static long swaps;

    public static void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int end = arr.length - 1; // last index that still needs to be compared
        while (end > 0) {
            int lastSwap = 0;
            for (int j = 0; j < end; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    lastSwap = j;
                }
            }
            end = lastSwap; // no swap in this pass -> lastSwap = 0 -> loop ends
        }
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(data));
        sort(data);
        System.out.println("After : " + Arrays.toString(data));
        System.out.println("Comparisons: " + comparisons + ", swaps: " + swaps);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        sort(sorted);
        System.out.println("Already sorted array -> comparisons: " + comparisons
                + " (just n-1), swaps: " + swaps);

        int[] nearlySorted = {1, 2, 4, 3, 5, 6, 7};
        sort(nearlySorted);
        System.out.println("Nearly sorted array  -> comparisons: " + comparisons + ", swaps: " + swaps);
    }
}
