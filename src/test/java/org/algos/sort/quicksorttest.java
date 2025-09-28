package org.algos.sort;

import org.junit.jupiter.api.Test;
import org.algos.metrics.metrics;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class quicksorttest {
    @Test
    public void testRandomArray() {
        int[] arr = {5, 3, 8, 1, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        metrics metrics = new metrics();
        quicksort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
    }
}

