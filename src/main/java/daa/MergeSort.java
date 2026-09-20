package daa;

public class MergeSort {

    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }

        int[] temp = new int[a.length];

        long start = System.nanoTime();

        mergeSort(a, temp, 0, a.length - 1, metrics, 1);

        metrics.timeNano = System.nanoTime() - start;
    }

    private static void mergeSort(int[] a, int[] temp,
                                  int left, int right,
                                  Metrics metrics, int depth) {

        metrics.updateDepth(depth);

        if (left >= right) {
            return;
        }

        if (right - left + 1 <= CUTOFF) {
            insertionSort(a, left, right, metrics);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(a, temp, left, mid, metrics, depth + 1);
        mergeSort(a, temp, mid + 1, right, metrics, depth + 1);

        merge(a, temp, left, mid, right, metrics);
    }
    private static void insertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= left) {
                metrics.comparisons++;

                if (a[j] <= key) {
                    break;
                }

                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = key;
        }
    }
    private static void merge(int[] a, int[] temp,
                              int left, int mid, int right,
                              Metrics metrics) {

        for (int i = left; i <= right; i++) {
            temp[i] = a[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.comparisons++;

            if (temp[i] <= temp[j]) {
                a[k] = temp[i];
                i++;
            } else {
                a[k] = temp[j];
                j++;
            }

            k++;
        }

        while (i <= mid) {
            a[k] = temp[i];
            i++;
            k++;
        }

        while (j <= right) {
            a[k] = temp[j];
            j++;
            k++;
        }
    }

}