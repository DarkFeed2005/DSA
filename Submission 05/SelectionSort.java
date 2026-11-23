import java.util.Arrays;
import java.util.Random;

/**
 * Selection Sort.
 * On each pass, find the smallest item in the unsorted part and swap it into the next
 * position of the sorted part at the front.
 * It makes at most n-1 swaps, which is useful when writing to memory is expensive,
 * but it always does n(n-1)/2 comparisons, even if the array is already sorted.
 *
 * Time: best, average and worst O(n^2)    Space: O(1)    Stable: no
 */
public class SelectionSort {
    public static long comparisons;
    public static long swaps;

    public static void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
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

        Random random = new Random(42);
        int[] big = new int[1000];
        for (int i = 0; i < big.length; i++) big[i] = random.nextInt(10000);
        int[] copy = big.clone();
        sort(big);
        Arrays.sort(copy);
        System.out.println("1000 random numbers sorted correctly: " + Arrays.equals(big, copy));
    }
}
