package org.algos.closest;

import org.algos.metrics.metrics;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class closestpairtest {

    private static final Random R = new Random(42);

    @Test
    void smallCheck() {
        point[] pts = {
                new point(0,0),
                new point(3,4),
                new point(1,1),
                new point(5,5)
        };
        double fast = closestpair.closest(pts, null);
        double brute = closestpair.bruteforce(pts);
        assertEquals(brute, fast, 1e-9);
    }

    @Test
    void randomUpTo2000() {
        for (int n : new int[]{10, 100, 500, 2000}) {
            point[] pts = new point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new point(R.nextDouble()*1e4, R.nextDouble()*1e4);
            }
            double fast = closestpair.closest(pts, null);
            double brute = closestpair.bruteforce(pts);
            assertEquals(brute, fast, 1e-8);
        }
    }

    @Test
    void largeRun() {
        int n = 50_000;
        point[] pts = new point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new point(R.nextDouble()*1e6, R.nextDouble()*1e6);
        }
        metrics m = new metrics();
        double res = closestpair.closest(pts, m);
        assertTrue(res >= 0.0);
        assertTrue(m.getMaxDepth() > 0);
    }
}
