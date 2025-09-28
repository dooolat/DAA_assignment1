package org.algos.metrics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MetricsTest {

    @Test
    void depthTrackingWorks() {
        metrics m = new metrics();
        m.enterRecursion(); // depth = 1
        m.enterRecursion(); // depth = 2
        m.exitRecursion();  // back to 1
        m.exitRecursion();  // back to 0
        assertEquals(2, m.getMaxDepth());
    }

    @Test
    void countersWork() {
        metrics m = new metrics();
        m.incComparisons();
        m.addAllocations(5);
        assertEquals(1, m.getComparisons());
        assertEquals(5, m.getAllocations());
    }

    @Test
    void timerWorks() throws InterruptedException {
        metrics m = new metrics();
        m.startTimer();
        Thread.sleep(10); // короткая пауза — гарантированно >0 ms
        m.stopTimer();
        assertTrue(m.getElapsedMillis() >= 0);
    }
}
