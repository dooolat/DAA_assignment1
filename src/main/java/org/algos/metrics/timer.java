package org.algos.metrics;

public class timer {
    private long start, end;

    public void start() {
        start = System.nanoTime();
    }

    public void stop() {
        end = System.nanoTime();
    }

    public long elapsed() {
        return (end - start) / 1_000_000; // ms
    }
}
