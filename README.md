# Assignment 1 — Divide & Conquer Algorithms

Project scope:
- MergeSort
- QuickSort
- Deterministic Select (Median-of-Medians)
- Closest Pair (2D)

Goals:
- Implement algorithms and measure performance (time, recursion depth, allocations).
- Document results in README.md (architecture, recurrence analysis, plots, summary).

Branches:
- main
- feature/mergesort
- feature/quicksort
- feature/select
- feature/closest
- feature/metrics

Initial commit includes: Maven setup, JUnit5, CI, README.

## Step 2: Metrics Implementation

In this step, we added basic metrics support:
- Counters to track events.
- Depth tracker for monitoring nesting levels.
- CSV writer for exporting metrics data.

These components provide the foundation for collecting and analyzing performance statistics.  
You can run the tests in `MetricsTest` and `CsvWriterTest` to verify the implementation.

