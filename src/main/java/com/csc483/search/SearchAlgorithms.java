package com.csc483.search;

public class SearchAlgorithms {

    /**
     * Sequential search by product ID.
     * Time Complexity: O(n)
     */
    public static Product sequentialSearchById(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == targetId) {
                return products[i];
            }
        }
        return null; // Not found
    }

    /**
     * Binary search by product ID (array must be sorted by ID).
     * Time Complexity: O(log n)
     */
    public static Product binarySearchById(Product[] products, int targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = products[mid].getProductId();

            if (midId == targetId) {
                return products[mid]; // Found
            } else if (midId < targetId) {
                left = mid + 1;       // Search right half
            } else {
                right = mid - 1;      // Search left half
            }
        }
        return null; // Not found
    }

    /**
     * Sequential search by product name (unsorted).
     * Time Complexity: O(n)
     */
    public static Product searchByName(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }
}