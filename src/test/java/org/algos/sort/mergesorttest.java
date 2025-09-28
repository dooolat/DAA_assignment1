package org.algos.sort;

import org.algos.metrics.metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class mergesorttest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        mergesort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();
        mergesort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Random rnd = new Random(42);
        int n = 2000;
        int[] arr = rnd.ints(n, -10000, 10000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);
        mergesort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testWithMetrics() {
        Random rnd = new Random(1);
        int[] arr = rnd.ints(500, 0, 1000).toArray();
        metrics m = new metrics();
        mergesort.sort(arr, m);
        // correctness
        int[] expected = arr.clone();
        Arrays.sort(expected);
        assertArrayEquals(expected, arr);
        // sanity checks on metrics
        assertTrue(m.getComparisons() > 0);
        assertTrue(m.getMaxDepth() > 0);
    }
}
