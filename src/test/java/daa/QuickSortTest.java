package daa;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testRandomArrays() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1000);
            int[] actual = new int[size];

            for (int i = 0; i < size; i++) {
                actual[i] = random.nextInt(10000);
            }

            int[] expected = actual.clone();

            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            QuickSort.sort(actual, metrics);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testEmptyArray() {
        int[] actual = {};

        QuickSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{}, actual);
    }

    @Test
    public void testOneElement() {
        int[] actual = {5};

        QuickSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{5}, actual);
    }

    @Test
    public void testAllEqual() {
        int[] actual = {7, 7, 7, 7, 7};

        QuickSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, actual);
    }

    @Test
    public void testAlreadySorted() {
        int[] actual = {1, 2, 3, 4, 5};

        QuickSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, actual);
    }

    @Test
    public void testRecursionDepth() {
        int n = 100000;

        int[] actual = new int[n];

        for (int i = 0; i < n; i++) {
            actual[i] = i;
        }

        Metrics metrics = new Metrics();

        QuickSort.sort(actual, metrics);

        double limit = 2 * (Math.log(n) / Math.log(2));

        assertTrue(
                metrics.maxDepth <= limit,
                "Recursion depth is too large: " + metrics.maxDepth
        );
    }
}