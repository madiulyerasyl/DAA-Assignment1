package daa;

public class QuickSelect {

    public static int select(int[] a, int k) {
        return select(a, k, new Metrics());
    }

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k is out of range");
        }

        long start = System.nanoTime();

        int result = selectPart(a, k, 0, a.length - 1, metrics);

        metrics.timeNano = System.nanoTime() - start;

        return result;
    }
    private static int selectPart(int[] a, int k, int left, int right,
                                  Metrics metrics) {

        while (left <= right) {

            int[] equal = QuickSort.partition(a, left, right, metrics);

            if (k < equal[0]) {
                right = equal[0] - 1;

            } else if (k > equal[1]) {
                left = equal[1] + 1;

            } else {
                return a[k];
            }
        }

        throw new IllegalStateException("Selection failed");
    }
}