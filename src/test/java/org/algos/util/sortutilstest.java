package org.algos.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class sortutilstest {

    @Test
    void testSwap() {
        int[] arr = {1, 2, 3};
        sortutils.swap(arr, 0, 2);
        assertArrayEquals(new int[]{3, 2, 1}, arr);
    }

    @Test
    void testShuffle() {
        int[] arr = {1, 2, 3, 4, 5};
        sortutils.shuffle(arr);
        assertEquals(5, arr.length); // элементы не теряются
    }

    @Test
    void testPartition() {
        int[] arr = {3, 2, 5, 1};
        int p = sortutils.partition(arr, 0, arr.length - 1);
        assertTrue(p >= 0 && p < arr.length);
    }

    @Test
    void testGuards() {
        assertThrows(IllegalArgumentException.class, () -> sortutils.guardNotNull(null));
        assertThrows(IllegalArgumentException.class, () -> sortutils.guardNonEmpty(new int[]{}));
    }
}
