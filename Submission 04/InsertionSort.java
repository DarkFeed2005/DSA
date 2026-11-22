import java.util.Arrays;

/**
 * Insertion Sort.
 * Builds a sorted section at the front of the array one item at a time: take the next
 * item (the key), shift every larger item in the sorted section one place to the right,
 * and drop the key into the gap - like sorting playing cards in your hand.
 *
 * Time: best O(n) (already sorted), average and worst O(n^2)    Space: O(1)    Stable: yes
 */
public class InsertionSort {
    public static long comparisons;
    public static long shifts;

    public static void sort(int[] arr) {
        comparisons = 0;
        shifts = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // shift right
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
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
        System.out.println("Already sorted array -> comparisons: " + comparisons
                + " (just n-1), shifts: " + shifts);

        int[] reversed = {7, 6, 5, 4, 3, 2, 1};
        sort(reversed);
        System.out.println("Reverse sorted array -> comparisons: " + comparisons + ", shifts: " + shifts);
    }
}
