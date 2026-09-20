package daa;

import java.util.Random;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {

    private static final int[] SIZES = {
            1000,
            10000,
            100000,
            1000000
    };

    private static final int RUNS = 5;

    private static final Random random = new Random();

    public static void main(String[] args) {

        System.out.println("Benchmark started");

        try (FileWriter writer = new FileWriter("results.csv")) {

            writer.write(
                    "algorithm,input,n,time_ms,comparisons,max_depth\n"
            );

            for (int n : SIZES) {

                System.out.println("Testing n = " + n);

                int[] randomArray = createRandomArray(n);
                int[] sortedArray = createSortedArray(n);
                int[] duplicatesArray = createDuplicatesArray(n);

                testInput(writer, "random", n, randomArray);
                testInput(writer, "sorted", n, sortedArray);
                testInput(writer, "duplicates", n, duplicatesArray);
            }

            System.out.println("Benchmark finished");
            System.out.println("results.csv created");

        } catch (IOException e) {
            System.out.println("Error creating CSV: " + e.getMessage());
        }
    }

    private static void testInput(FileWriter writer,
                                  String inputName,
                                  int n,
                                  int[] original) throws IOException {

        String[] algorithms = {
                "MergeSort",
                "QuickSort",
                "QuickSelect"
        };

        for (String algorithm : algorithms) {

            RunResult result = runFiveTimes(algorithm, original);

            writer.write(
                    algorithm + "," +
                            inputName + "," +
                            n + "," +
                            result.timeMs + "," +
                            result.comparisons + "," +
                            result.maxDepth + "\n"
            );

            System.out.println(
                    algorithm + " | " +
                            inputName + " | n=" + n +
                            " | " + result.timeMs + " ms"
            );
        }
    }

    private static int[] createRandomArray(int n) {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(1000000);
        }

        return a;
    }

    private static int[] createSortedArray(int n) {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        return a;
    }

    private static int[] createDuplicatesArray(int n) {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(10);
        }

        return a;
    }
    private static class RunResult {
        double timeMs;
        long comparisons;
        int maxDepth;

        RunResult(double timeMs, long comparisons, int maxDepth) {
            this.timeMs = timeMs;
            this.comparisons = comparisons;
            this.maxDepth = maxDepth;
        }
    }
    private static RunResult runMergeSort(int[] original) {
        int[] a = original.clone();

        Metrics metrics = new Metrics();

        MergeSort.sort(a, metrics);

        return new RunResult(
                metrics.getTimeMs(),
                metrics.comparisons,
                metrics.maxDepth
        );
    }
    private static RunResult runQuickSort(int[] original) {
        int[] a = original.clone();

        Metrics metrics = new Metrics();

        QuickSort.sort(a, metrics);

        return new RunResult(
                metrics.getTimeMs(),
                metrics.comparisons,
                metrics.maxDepth
        );
    }

    private static RunResult runQuickSelect(int[] original) {
        int[] a = original.clone();

        Metrics metrics = new Metrics();

        int k = a.length / 2;

        QuickSelect.select(a, k, metrics);

        return new RunResult(
                metrics.getTimeMs(),
                metrics.comparisons,
                metrics.maxDepth
        );
    }
    private static RunResult runFiveTimes(String algorithm, int[] original) {
        RunResult[] results = new RunResult[RUNS];

        for (int i = 0; i < RUNS; i++) {
            if (algorithm.equals("MergeSort")) {
                results[i] = runMergeSort(original);
            } else if (algorithm.equals("QuickSort")) {
                results[i] = runQuickSort(original);
            } else if (algorithm.equals("QuickSelect")) {
                results[i] = runQuickSelect(original);
            }
        }

        Arrays.sort(results, (a, b) ->
                Double.compare(a.timeMs, b.timeMs));

        return results[RUNS / 2];
    }
}