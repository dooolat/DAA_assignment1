package bench;

import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.algos.sort.mergesort;
import org.algos.sort.quicksort;
import org.algos.sort.deterministicselect;
import org.algos.metrics.metrics;

/**
 * Benchmark harness to compare deterministic select vs sorting algorithms.
 * Adjusted for lowercase class names and static methods.
 */
@BenchmarkMode(Mode.AverageTime) // measure average execution time
@OutputTimeUnit(TimeUnit.MILLISECONDS) // results in milliseconds
@State(Scope.Thread) // each thread gets its own state
public class selectvssortbenchmark {

    @Param({"1000", "5000", "10000"}) // array sizes to test
    public int n;

    private int[] array;

    /**
     * Setup method runs before each benchmark invocation.
     * Initializes a new random array.
     */
    @Setup(Level.Invocation)
    public void setup() {
        Random rnd = new Random();
        array = new int[n];
        for (int i = 0; i < n; i++) array[i] = rnd.nextInt();
    }

    /**
     * Benchmark mergesort on a cloned array.
     * @return first element to prevent JVM optimization
     */
    @Benchmark
    public int benchmergesort() {
        int[] copy = array.clone();
        mergesort.sort(copy); // static public method without metrics
        return copy[0];
    }

    /**
     * Benchmark quicksort on a cloned array.
     * @return first element to prevent JVM optimization
     */
    @Benchmark
    public int benchquicksort() {
        int[] copy = array.clone();
        metrics m = new metrics();       // create metrics for QuickSort
        quicksort.sort(copy, m);         // static method requires metrics
        return copy[0];
    }

    /**
     * Benchmark deterministic select (median-of-medians) on a cloned array.
     * @return median value to prevent JVM optimization
     */
    @Benchmark
    public int benchselect() {
        int k = n / 2;                   // median index
        metrics m = new metrics();        // create metrics for deterministic select
        return deterministicselect.select(array.clone(), k, m); // static method with metrics
    }
}
