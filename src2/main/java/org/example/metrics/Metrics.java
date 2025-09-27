package org.example.metrics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Простая перезапускаемая структура для сбора метрик одного прогона:
 * - comparisons, allocations (AtomicLong)
 * - max recursion depth (AtomicInteger + ThreadLocal current depth)
 * - simple timer (nanoTime)
 *
 * Дизайн: экземпляр Metrics используется на один эксперимент/прогон.
 */
public final class Metrics {
    private final AtomicLong comparisons = new AtomicLong(0);
    private final AtomicLong allocations = new AtomicLong(0);
    private final AtomicInteger maxDepth = new AtomicInteger(0);
    private final ThreadLocal<Integer> curDepth = ThreadLocal.withInitial(() -> 0);

    private volatile long startNano = 0;
    private volatile long endNano = 0;

    public void incComparisons() { comparisons.incrementAndGet(); }
    public void addComparisons(long n) { comparisons.addAndGet(n); }

    public void addAllocations(long n) { allocations.addAndGet(n); }

    public void enterRecursion() {
        int d = curDepth.get() + 1;
        curDepth.set(d);
        maxDepth.getAndUpdate(prev -> Math.max(prev, d));
    }

    public void exitRecursion() {
        curDepth.set(Math.max(0, curDepth.get() - 1));
    }

    public void startTimer() {
        startNano = System.nanoTime();
        endNano = 0;
    }

    public void stopTimer() {
        endNano = System.nanoTime();
    }

    public long getComparisons() { return comparisons.get(); }
    public long getAllocations() { return allocations.get(); }
    public int getMaxDepth() { return maxDepth.get(); }

    /**
     * elapsed in milliseconds (if stopTimer not called, returns time since start)
     */
    public long getElapsedMillis() {
        if (startNano == 0) return 0;
        long end = (endNano == 0) ? System.nanoTime() : endNano;
        return Math.max(0L, (end - startNano) / 1_000_000L);
    }

    public void resetForRun() {
        comparisons.set(0);
        allocations.set(0);
        maxDepth.set(0);
        curDepth.set(0);
        startNano = 0;
        endNano = 0;
    }

    public static String csvHeader() {
        return "n,elapsedMillis,comparisons,allocations,maxDepth";
    }

    public String toCsvLine(int n) {
        return n + "," + getElapsedMillis() + "," + getComparisons() + "," + getAllocations() + "," + getMaxDepth();
    }
}
