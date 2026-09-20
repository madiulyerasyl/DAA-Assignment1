package daa;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] numbers = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15,
                16, 17, 18, 19, 20
        };

        Metrics metrics = new Metrics();

        System.out.println("Before: " + Arrays.toString(numbers));

        QuickSort.sort(numbers, metrics);

        System.out.println("After:  " + Arrays.toString(numbers));
        System.out.println("Comparisons: " + metrics.comparisons);
        System.out.println("Max depth: " + metrics.maxDepth);
        System.out.println("Time: " + metrics.getTimeMs() + " ms");
    }
}