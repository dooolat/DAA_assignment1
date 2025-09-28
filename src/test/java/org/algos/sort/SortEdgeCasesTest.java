package org.algos.sort;

import org.algos.metrics.metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortEdgeCasesTest {

    @Test
    void testTinyArrays() {
        metrics m = new metrics();

        // Single element
        int[] a1 = {42};
        mergesort.sort(a1, m);
        assertArrayEquals(new int[]{42}, a1);

        int[] b1 = {7};
        quicksort.sort(b1, m);
        assertArrayEquals(new int[]{7}, b1);

        // Two elements
        int[] a2 = {5, 3};
        mergesort.sort(a2, m);
        assertArrayEquals(new int[]{3,5}, a2);

        int[] b2 = {5, 3};
        quicksort.sort(b2, m);
        assertArrayEquals(new int[]{3,5}, b2);
    }

    @Test
    void testDuplicates() {
        metrics m = new metrics();

        int[] a = {2, 2, 2, 2, 2};
        mergesort.sort(a, m);
        assertArrayEquals(new int[]{2,2,2,2,2}, a);

        int[] b = {1, 3, 3, 1, 2};
        quicksort.sort(b, m);
        assertArrayEquals(new int[]{1,1,2,3,3}, b);
    }

    @Test
    void testRandomSmallArrays() {
        metrics m = new metrics();

        int[] arr = {5,1,4,2,3};
        int[] copy = arr.clone();
        Arrays.sort(copy);

        mergesort.sort(arr, m);
        assertArrayEquals(copy, arr);

        int[] arr2 = {9,7,8,6,5};
        int[] copy2 = arr2.clone();
        Arrays.sort(copy2);

        quicksort.sort(arr2, m);
        assertArrayEquals(copy2, arr2);
    }
}
