package daa;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] numbers = {
                20, 5, 18, 2, 15,
                7, 11, 1, 19, 4,
                13, 8, 16, 3, 10,
                6, 17, 9, 14, 12
        };

        Metrics metrics = new Metrics();

        System.out.println("Before: " + Arrays.toString(numbers));

        MergeSort.sort(numbers, metrics);

        System.out.println("After:  " + Arrays.toString(numbers));
        System.out.println("Comparisons: " + metrics.comparisons);
        System.out.println("Max depth: " + metrics.maxDepth);
        System.out.println("Time: " + metrics.getTimeMs() + " ms");
    }
}