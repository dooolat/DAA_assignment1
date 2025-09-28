package org.algos.sort;

import org.algos.metrics.metrics;
import org.algos.util.sortutils;
import java.util.Random;

public class quicksort {
    private static Random rand = new Random();

    public static void sort(int[] arr, metrics metrics) {
        sortutils.guardNotNull(arr);
        sortutils.guardNonEmpty(arr);
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int low, int high, metrics metrics) {
        if (low < high) {
            metrics.enterRecursion(); // начинаем рекурсию
            int pivotIndex = partition(arr, low, high, metrics);

            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1, metrics);
                quickSort(arr, pivotIndex + 1, high, metrics);
            } else {
                quickSort(arr, pivotIndex + 1, high, metrics);
                quickSort(arr, low, pivotIndex - 1, metrics);
            }

            metrics.exitRecursion(); // заканчиваем рекурсию
        }
    }

    private static int partition(int[] arr, int low, int high, metrics metrics) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        sortutils.swap(arr, pivotIndex, high);
        metrics.addAllocations(1);

        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            metrics.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                sortutils.swap(arr, i, j);
                metrics.addAllocations(1);
            }
        }
        sortutils.swap(arr, i + 1, high);
        metrics.addAllocations(1);
        return i + 1;
    }
}
