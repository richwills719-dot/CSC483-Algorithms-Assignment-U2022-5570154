package com.csc483.sorting;

public class SortingAlgorithms {

    public static long comparisonCount = 0;
    public static long swapCount = 0;

    public static void resetCounters() {
        comparisonCount = 0;
        swapCount = 0;
    }

    // ===================== INSERTION SORT =====================
    // O(n) best, O(n²) average/worst
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                comparisonCount++;
                arr[j + 1] = arr[j];
                swapCount++;
                j--;
            }
            comparisonCount++;
            arr[j + 1] = key;
        }
    }

    // ===================== MERGE SORT =====================
    // O(n log n) all cases
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            comparisonCount++;
            if (L[i] <= R[j]) { arr[k++] = L[i++]; }
            else               { arr[k++] = R[j++]; swapCount++; }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ===================== QUICK SORT =====================
    // O(n log n) average, O(n²) worst
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisonCount++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                swapCount++;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
}