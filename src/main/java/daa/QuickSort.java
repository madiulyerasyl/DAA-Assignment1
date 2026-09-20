package daa;

import java.util.Random;

public class QuickSort {

    private static final Random random = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }

        long start = System.nanoTime();

        quickSort(a, 0, a.length - 1, metrics, 1);

        metrics.timeNano = System.nanoTime() - start;
    }
    private static void quickSort(int[] a, int left, int right,
                                  Metrics metrics, int depth) {

        while (left < right) {

            metrics.updateDepth(depth);

            int[] equal = partition(a, left, right, metrics);

            int leftSize = equal[0] - left;
            int rightSize = right - equal[1];

            if (leftSize < rightSize) {

                if (left < equal[0] - 1) {
                    quickSort(a, left, equal[0] - 1, metrics, depth + 1);
                }

                left = equal[1] + 1;

            } else {

                if (equal[1] + 1 < right) {
                    quickSort(a, equal[1] + 1, right, metrics, depth + 1);
                }

                right = equal[0] - 1;
            }
        }
    }
    private static int[] partition(int[] a, int left, int right,
                                   Metrics metrics) {

        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = a[pivotIndex];

        int less = left;
        int i = left;
        int greater = right;

        while (i <= greater) {

            metrics.comparisons++;

            if (a[i] < pivot) {
                swap(a, i, less);
                i++;
                less++;

            } else {
                metrics.comparisons++;

                if (a[i] > pivot) {
                    swap(a, i, greater);
                    greater--;
                } else {
                    i++;
                }
            }
        }

        return new int[]{less, greater};
    }
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}