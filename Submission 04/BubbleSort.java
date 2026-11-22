import java.util.Arrays;

/**
 * Bubble Sort (basic version).
 * Repeatedly compares neighbouring items and swaps them if they are in the wrong order,
 * so after each pass the largest remaining item "bubbles" to the end.
 * This version always performs every pass, even if the array is already sorted.
 *
 * Time: O(n^2) in best, average and worst case    Space: O(1)    Stable: yes
 */
public class BubbleSort {
    public static long comparisons;
    public static long swaps;

    public static void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int pass = 0; pass < n - 1; pass++) {
            for (int j = 0; j < n - 1 - pass; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
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
                + " (still n(n-1)/2), swaps: " + swaps);
    }
}
