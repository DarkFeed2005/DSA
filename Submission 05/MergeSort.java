import java.util.Arrays;
import java.util.Random;

/**
 * Merge Sort (top-down, divide and conquer).
 *   1. Divide: split the array into two halves.
 *   2. Conquer: sort each half recursively.
 *   3. Combine: merge the two sorted halves into one sorted array.
 * It always splits evenly, so the running time does not depend on the input order.
 *
 * Time: best, average and worst O(n log n)    Space: O(n) for the helper array    Stable: yes
 */
public class MergeSort {
    public static long comparisons;

    public static void sort(int[] arr) {
        comparisons = 0;
        if (arr.length < 2) return;
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] aux, int low, int high) {
        if (low >= high) return;
        int mid = low + (high - low) / 2;
        mergeSort(arr, aux, low, mid);
        mergeSort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);
    }

    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k]; // copy the range, then merge back into arr
        }
        int i = low;      // next item of the left half
        int j = mid + 1;  // next item of the right half
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > high) {
                arr[k] = aux[i++];
            } else {
                comparisons++;
                if (aux[j] < aux[i]) {
                    arr[k] = aux[j++];
                } else {
                    arr[k] = aux[i++]; // ties take the left item, so the sort is stable
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(data));
        sort(data);
        System.out.println("After : " + Arrays.toString(data));
        System.out.println("Comparisons: " + comparisons);

        Random random = new Random(42);
        int[] big = new int[100000];
        for (int i = 0; i < big.length; i++) big[i] = random.nextInt(1000000);
        int[] copy = big.clone();
        sort(big);
        Arrays.sort(copy);
        System.out.println("100000 random numbers sorted correctly: " + Arrays.equals(big, copy)
                + " (comparisons: " + comparisons + ")");
    }
}
