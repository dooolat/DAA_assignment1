package org.algos.sort;

import org.algos.metrics.metrics;

public class deterministicselect {

    // Main select method without metrics
    public static int select(int[] arr, int low, int high, int k) {
        if (low == high) {
            return arr[low];
        }
        int pivotIndex = medianOfMedians(arr, low, high);
        pivotIndex = partitionAroundPivot(arr, low, high, pivotIndex, k);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return select(arr, low, pivotIndex - 1, k);
        } else {
            return select(arr, pivotIndex + 1, high, k);
        }
    }

    // Overloaded method with metrics (wrapper for tests)
    public static int select(int[] arr, int k, metrics m) {
        if (m != null) {
            // You can increment metrics here if needed
            m.incComparisons();; // at least count one call
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int partitionAroundPivot(int[] arr, int low, int high, int pivotIndex, int k) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, high);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int low, int high) {
        int n = high - low + 1;
        if (n <= 5) {
            java.util.Arrays.sort(arr, low, high + 1);
            return low + n / 2;
        }
        int numMedians = 0;
        for (int i = low; i <= high; i += 5) {
            int subRight = Math.min(i + 4, high);
            java.util.Arrays.sort(arr, i, subRight + 1);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, low + numMedians, medianIndex);
            numMedians++;
        }
        return medianOfMedians(arr, low, low + numMedians - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr.length) {
            throw new IndexOutOfBoundsException("Swap indices out of bounds");
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
