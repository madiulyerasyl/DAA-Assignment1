package daa;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] numbers = {8, 2, 5, 1, 9, 3, 7, 4, 6};

        Metrics metrics = new Metrics();

        int k = 4;

        int result = QuickSelect.select(numbers, k, metrics);

        System.out.println("k = " + k);
        System.out.println("k-th smallest = " + result);
        System.out.println("Comparisons: " + metrics.comparisons);
        System.out.println("Time: " + metrics.getTimeMs() + " ms");
    }
}