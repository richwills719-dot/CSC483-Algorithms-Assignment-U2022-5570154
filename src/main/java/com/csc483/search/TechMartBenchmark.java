package com.csc483.search;

import java.util.Arrays;
import java.util.Random;

public class TechMartBenchmark {

    private static final int DATASET_SIZE = 100_000;
    private static final int MAX_ID = 200_000;
    private static final String[] CATEGORIES = {"Laptop", "Phone", "Tablet", "Camera", "Headphones"};
    private static final String[] NAMES = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon"};

    public static void main(String[] args) {
        // Generate dataset
        Product[] products = generateProducts(DATASET_SIZE);

        // Sort by ID for binary search
        Arrays.sort(products);

        System.out.println("================================================================");
        System.out.println("TECHMART SEARCH PERFORMANCE ANALYSIS (n = 100,000 products)");
        System.out.println("================================================================");

        // --- SEQUENTIAL SEARCH TESTS ---
        System.out.println("\nSEQUENTIAL SEARCH:");

        // Best case: target is the first element
        int bestCaseId = products[0].getProductId();
        long start = System.nanoTime();
        SearchAlgorithms.sequentialSearchById(products, bestCaseId);
        long end = System.nanoTime();
        System.out.printf("Best Case (ID found at position 0): %.3f ms%n", (end - start) / 1_000_000.0);

        // Average case: random ID
        int randomId = products[DATASET_SIZE / 2].getProductId();
        start = System.nanoTime();
        SearchAlgorithms.sequentialSearchById(products, randomId);
        end = System.nanoTime();
        System.out.printf("Average Case (random ID):           %.3f ms%n", (end - start) / 1_000_000.0);

        // Worst case: ID doesn't exist
        start = System.nanoTime();
        SearchAlgorithms.sequentialSearchById(products, -1);
        end = System.nanoTime();
        System.out.printf("Worst Case (ID not found):          %.3f ms%n", (end - start) / 1_000_000.0);

        // --- BINARY SEARCH TESTS ---
        System.out.println("\nBINARY SEARCH:");

        start = System.nanoTime();
        SearchAlgorithms.binarySearchById(products, bestCaseId);
        end = System.nanoTime();
        System.out.printf("Best Case (ID at middle):           %.3f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        SearchAlgorithms.binarySearchById(products, randomId);
        end = System.nanoTime();
        System.out.printf("Average Case (random ID):           %.3f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        SearchAlgorithms.binarySearchById(products, -1);
        end = System.nanoTime();
        System.out.printf("Worst Case (ID not found):          %.3f ms%n", (end - start) / 1_000_000.0);

        System.out.println("\n================================================================");
    }

    private static Product[] generateProducts(int size) {
        Random rand = new Random(42); // Fixed seed for reproducibility
        Product[] products = new Product[size];
        java.util.Set<Integer> usedIds = new java.util.HashSet<>();

        for (int i = 0; i < size; i++) {
            int id;
            do {
                id = rand.nextInt(MAX_ID) + 1;
            } while (usedIds.contains(id));
            usedIds.add(id);

            String name = NAMES[rand.nextInt(NAMES.length)] + "-" + id;
            String category = CATEGORIES[rand.nextInt(CATEGORIES.length)];
            double price = 10.0 + rand.nextDouble() * 990.0;
            int stock = rand.nextInt(100);

            products[i] = new Product(id, name, category, price, stock);
        }
        return products;
    }
}