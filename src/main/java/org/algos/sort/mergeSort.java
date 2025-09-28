package org.algos.sort;

import org.algos.metrics.metrics;
import org.algos.util.sortutils;

/**
 * MergeSort with:
 * - reusable buffer
 * - small-n cutoff to insertion sort (CUT_OFF)
 * - optional Metrics parameter to collect comparisons/allocations/depth
 */
public final class mergeSort {

    private static final int CUT_OFF = 16;

    private mergeSort() {}

    /** Public API without metrics */
    public static void sort(int[] a) {
        sort(a, null);
    }

    /** Public API with optional metrics */
    public static void sort(int[] a, metrics metrics) {
        sortutils.guardNotNull(a);
        if (a.length <= 1) return;

        if (metrics != null) metrics.startTimer();

        // allocate one reusable buffer and count allocation
        int[] buffer = new int[a.length];
        if (metrics != null) metrics.addAllocations(a.length);

        mergeSort(a, buffer, 0, a.length - 1, metrics);

        if (metrics != null) metrics.stopTimer();
    }

    private static void mergeSort(int[] a, int[] buffer, int left, int right, metrics metrics) {
        if (metrics != null) metrics.enterRecursion();

        int n = right - left + 1;
        if (n <= CUT_OFF) {
            insertionSort(a, left, right, metrics);
            if (metrics != null) metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(a, buffer, left, mid, metrics);
        mergeSort(a, buffer, mid + 1, right, metrics);

        if (a[mid] <= a[mid + 1]) {
            if (metrics != null) metrics.incComparisons();
            if (metrics != null) metrics.exitRecursion();
            return;
        }

        merge(a, buffer, left, mid, right, metrics);
        if (metrics != null) metrics.exitRecursion();
    }

    private static void merge(int[] a, int[] buffer, int left, int mid, int right, metrics metrics) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (metrics != null) metrics.incComparisons();
            if (a[i] <= a[j]) buffer[k++] = a[i++];
            else buffer[k++] = a[j++];
        }
        while (i <= mid) buffer[k++] = a[i++];
        while (j <= right) buffer[k++] = a[j++];

        // copy back
        for (int p = left; p <= right; p++) {
            a[p] = buffer[p];
        }
    }

    private static void insertionSort(int[] a, int left, int right, metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int v = a[i];
            int j = i - 1;
            while (j >= left) {
                if (metrics != null) metrics.incComparisons();
                if (a[j] <= v) break;
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = v;
        }
    }
}
