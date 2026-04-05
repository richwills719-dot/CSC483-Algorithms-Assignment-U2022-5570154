package com.csc483.sorting;

import java.util.Arrays;
import java.util.Random;

public class SortingBenchmark {

    private static final int[] SIZES = {100, 1000, 10_000, 100_000};
    private static final int RUNS = 5;

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("SORTING ALGORITHMS COMPARISON");
        System.out.println("================================================================");
        System.out.printf("%-12s %-15s %-12s %-15s %-12s%n",
                "Input Size", "Algorithm", "Time (ms)", "Comparisons", "Swaps");
        System.out.println("----------------------------------------------------------------");

        for (int size : SIZES) {
            runBenchmark("Random",        generateRandom(size),      size);
            runBenchmark("Sorted",        generateSorted(size),      size);
            runBenchmark("Reverse",       generateReverse(size),     size);
            runBenchmark("NearlySorted",  generateNearlySorted(size),size);
            runBenchmark("Duplicates",    generateDuplicates(size),  size);
        }
    }

    private static void runBenchmark(String dataType, int[] original, int size) {
        String[] algorithms = {"Insertion", "Merge", "Quick"};
        for (String algo : algorithms) {
            int[] data = Arrays.copyOf(original, original.length);
            SortingAlgorithms.resetCounters();

            long totalTime = 0;
            for (int r = 0; r < RUNS; r++) {
                int[] copy = Arrays.copyOf(data, data.length);
                SortingAlgorithms.resetCounters();
                long start = System.nanoTime();
                switch (algo) {
                    case "Insertion" -> SortingAlgorithms.insertionSort(copy);
                    case "Merge"     -> SortingAlgorithms.mergeSort(copy, 0, copy.length - 1);
                    case "Quick"     -> SortingAlgorithms.quickSort(copy, 0, copy.length - 1);
                }
                totalTime += System.nanoTime() - start;
            }

            double avgMs = totalTime / RUNS / 1_000_000.0;
            System.out.printf("%-12d %-15s %-12.3f %-15d %-12d  [%s]%n",
                    size, algo, avgMs,
                    SortingAlgorithms.comparisonCount,
                    SortingAlgorithms.swapCount,
                    dataType);
        }
    }

    // ===== Data Generators =====
    static int[] generateRandom(int n) {
        Random r = new Random(42);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(n * 10);
        return a;
    }
    static int[] generateSorted(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }
    static int[] generateReverse(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - i;
        return a;
    }
    static int[] generateNearlySorted(int n) {
        int[] a = generateSorted(n);
        Random r = new Random(42);
        int swaps = (int)(n * 0.1);
        for (int i = 0; i < swaps; i++) {
            int x = r.nextInt(n), y = r.nextInt(n);
            int tmp = a[x]; a[x] = a[y]; a[y] = tmp;
        }
        return a;
    }
    static int[] generateDuplicates(int n) {
        Random r = new Random(42);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(10); // Only 10 distinct values
        return a;
    }
}