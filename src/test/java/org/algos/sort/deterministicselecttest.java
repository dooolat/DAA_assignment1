package org.algos.sort;

import org.algos.metrics.metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class deterministicselecttest {

    @Test
    void testSmallAndEdgeCases() {
        int[] a = {5};
        metrics m = new metrics();
        assertEquals(5, deterministicselect.select(a, 0, m));

        int[] b = {3, 1};
        assertEquals(1, deterministicselect.select(b, 0, null));
        assertEquals(3, deterministicselect.select(b, 1, null));
    }

    @Test
    void testRandomTrialsCompareWithSort() {
        Random rnd = new Random(123);
        for (int t = 0; t < 100; t++) {
            int n = 50 + rnd.nextInt(150); // 50..199
            int[] a = rnd.ints(n, -10000, 10000).toArray();
            int k = rnd.nextInt(n);
            int[] copy = a.clone();
            Arrays.sort(copy);
            metrics m = new metrics();
            int res = deterministicselect.select(a, k, m);
            assertEquals(copy[k], res, "Mismatch at trial " + t + " k=" + k);
            // sanity: metrics collected
            assertTrue(m.getComparisons() >= 0);
            assertTrue(m.getMaxDepth() >= 0);
        }
    }
}
