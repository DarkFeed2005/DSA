import java.util.Arrays;
import java.util.Random;

/**
 * Quick Sort (basic version, Lomuto partition, last element as pivot).
 *   1. Choose the last item as the pivot.
 *   2. Partition: move items smaller than or equal to the pivot to its left and larger
 *      items to its right, so the pivot lands in its final position.
 *   3. Recursively sort the left part and the right part.
 *
 * Weakness: on an already sorted array this pivot choice is the worst case - every
 * partition is lopsided, giving O(n^2) time and O(n) recursion depth. Better pivot
 * selection is covered by the optimized version.
 *
 * Time: best and average O(n log n), worst O(n^2)
 * Space: O(log n) recursion on average, O(n) in the worst case    Stable: no
 */
public class QuickSort {
    public static long comparisons;
    public static long swaps;

    public static void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int p = partition(arr, low, high);
        quickSort(arr, low, p - 1);
        quickSort(arr, p + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // end of the "smaller or equal" region
        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high); // put the pivot in its final position
        return i + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        swaps++;
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(data));
        sort(data);
        System.out.println("After : " + Arrays.toString(data));
        System.out.println("Comparisons: " + comparisons + ", swaps: " + swaps);

        Random random = new Random(42);
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = random.nextInt(1000000);
        int[] copy = big.clone();
        sort(big);
        Arrays.sort(copy);
        System.out.println("100000 random numbers sorted correctly: " + Arrays.equals(big, copy)
                + " (comparisons: " + comparisons + ")");

        // Worst case for this pivot choice: an already sorted array (kept small to avoid deep recursion)
        int[] sorted = new int[2000];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;
        sort(sorted);
        System.out.println("2000 already sorted numbers -> comparisons: " + comparisons
                + " (about n^2/2 = " + (2000L * 1999 / 2) + ")");
    }
}
