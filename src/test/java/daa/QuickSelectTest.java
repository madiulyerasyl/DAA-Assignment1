package daa;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSelectTest {

    @Test
    public void testRandomArrays() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {

            int size = random.nextInt(999) + 1;
            int[] actual = new int[size];

            for (int i = 0; i < size; i++) {
                actual[i] = random.nextInt(10000);
            }

            int[] sorted = actual.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(size);

            Metrics metrics = new Metrics();

            int result = QuickSelect.select(actual, k, metrics);

            assertEquals(sorted[k], result);
        }
    }

    @Test
    public void testDuplicates() {
        int[] actual = {5, 2, 5, 1, 5, 3, 5};

        int result = QuickSelect.select(actual, 3, new Metrics());

        assertEquals(5, result);
    }

    @Test
    public void testFirstElement() {
        int[] actual = {8, 2, 5, 1, 9};

        int result = QuickSelect.select(actual, 0, new Metrics());

        assertEquals(1, result);
    }

    @Test
    public void testLastElement() {
        int[] actual = {8, 2, 5, 1, 9};

        int result = QuickSelect.select(actual, 4, new Metrics());

        assertEquals(9, result);
    }

    @Test
    public void testEmptyArray() {
        int[] actual = {};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(actual, 0, new Metrics())
        );
    }

    @Test
    public void testInvalidK() {
        int[] actual = {1, 2, 3};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(actual, 10, new Metrics())
        );
    }
}