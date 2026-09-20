package daa;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

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
            MergeSort.sort(actual, metrics);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void testEmptyArray() {
        int[] actual = {};

        MergeSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{}, actual);
    }

    @Test
    public void testOneElement() {
        int[] actual = {5};

        MergeSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{5}, actual);
    }

    @Test
    public void testAllEqual() {
        int[] actual = {7, 7, 7, 7, 7};

        MergeSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, actual);
    }

    @Test
    public void testAlreadySorted() {
        int[] actual = {1, 2, 3, 4, 5};

        MergeSort.sort(actual, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, actual);
    }
}