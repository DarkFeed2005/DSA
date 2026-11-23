import java.util.Arrays;
import java.util.Random;

/**
 * Optimized Quick Sort.
 * Fixes the weaknesses of the basic version (last-element pivot, deep recursion):
 *   1. Median-of-three pivot: the pivot is the median of the first, middle and last items,
 *      so sorted and reverse-sorted input no longer produce lopsided partitions.
 *   2. Three-way partitioning (Dutch national flag): items equal to the pivot are grouped
 *      in the middle and never sorted again, so arrays with many duplicates are fast
 *      (an array of all equal items takes O(n)).
 *   3. Insertion sort cut-off: tiny sub-arrays are finished with insertion sort, which has
 *      less overhead than recursion.
 *   4. Smaller side first: recurse into the smaller part and loop on the larger part, so
 *      the recursion depth is at most O(log n) and a stack overflow cannot happen.
 *
 * Time: best and average O(n log n); worst O(n^2) is possible but very unlikely
 * Space: O(log n) guaranteed    Stable: no
 */
public class OptimizedQuickSort {
    private static final int CUTOFF = 10;

    public static long comparisons;
    public static long swaps;

    public static void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            if (high - low + 1 <= CUTOFF) {
                insertionSort(arr, low, high);
                return;
            }

            int pivot = medianOfThree(arr, low, high);

            // three-way partition: [low..lt-1] < pivot, [lt..gt] == pivot, [gt+1..high] > pivot
            int lt = low, i = low, gt = high;
            while (i <= gt) {
                comparisons++;
                if (arr[i] < pivot) {
                    swap(arr, lt++, i++);
                } else {
                    comparisons++;
                    if (arr[i] > pivot) {
                        swap(arr, i, gt--);
                    } else {
                        i++;
                    }
                }
            }

            // recurse into the smaller part, loop on the larger part
            if (lt - low < high - gt) {
                quickSort(arr, low, lt - 1);
                low = gt + 1;
            } else {
                quickSort(arr, gt + 1, high);
                high = lt - 1;
            }
        }
    }

    private static int medianOfThree(int[] arr, int low, int high) {
        int a = arr[low];
        int b = arr[low + (high - low) / 2];
        int c = arr[high];
        if ((a <= b && b <= c) || (c <= b && b <= a)) return b;
        if ((b <= a && a <= c) || (c <= a && a <= b)) return a;
        return c;
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        swaps++;
    }

    private static void check(String label, int[] arr) {
        int[] expected = arr.clone();
        Arrays.sort(expected);
        sort(arr);
        System.out.printf("%-34s sorted correctly: %-5b comparisons: %d%n",
                label, Arrays.equals(arr, expected), comparisons);
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90, 5, 77, 1, 42, 18};
        System.out.println("Before: " + Arrays.toString(data));
        sort(data);
        System.out.println("After : " + Arrays.toString(data));
        System.out.println();

        int n = 100000;
        Random random = new Random(42);
        int[] randomData = new int[n];
        int[] sortedData = new int[n];
        int[] reverseData = new int[n];
        int[] equalData = new int[n];
        int[] fewDistinct = new int[n];
        for (int i = 0; i < n; i++) {
            randomData[i] = random.nextInt(1000000);
            sortedData[i] = i;
            reverseData[i] = n - i;
            equalData[i] = 7;
            fewDistinct[i] = random.nextInt(5);
        }
        check("100000 random numbers", randomData);
        check("100000 already sorted", sortedData);
        check("100000 reverse sorted", reverseData);
        check("100000 all equal", equalData);
        check("100000 with only 5 distinct values", fewDistinct);
    }
}
