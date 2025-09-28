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

## Progress

### Step 1: Project Initialization
- Created Maven project structure.
- Added JUnit 5 for testing.
- Configured CI and initial README.

### Step 2: Metrics Module
- Implemented counters for comparisons and allocations.
- Added recursion depth tracker.
- Implemented CSV writer for exporting results.
- Wrote unit tests for metrics components.

### Step 3: MergeSort Implementation
- Implemented MergeSort using divide-and-conquer (Master Theorem Case 2).
- Features:
    - Linear merge.
    - Reusable buffer to minimize allocations.
    - Small-n cut-off with Insertion Sort.
- Added unit tests for correctness and performance.
- Verified recursion depth and comparison counters via metrics.

### Step 4: QuickSort Implementation
- Implemented QuickSort with robust recursion and randomized pivot selection.
- Features:
  - Recurse on the smaller partition first to keep recursion depth bounded.
  - Randomized pivot to avoid worst-case performance.
  - Metrics tracking: comparisons, swaps, recursion depth, and elapsed time.
- Added unit tests for correctness on random and adversarial arrays.
- Verified recursion depth and metrics collection via `Metrics` module.

### Step 5: Utilities Refactor (SortUtils)

- Created SortUtils.java with reusable utility methods:
  - swap(int[] arr, int i, int j) — swaps elements.
  - shuffle(int[] arr, Random rand) — Fisher–Yates shuffle.
  - guardNotNull(Object obj, String msg) — checks null arguments.
- Updated MergeSort and QuickSort to use SortUtils.swap where applicable.
- Added unit tests in SortUtilsTest.java:
  - Verify swapping correctness.
  - Verify shuffling randomness and bounds.
  - Verify null guard throws exception.
- Commit message: feat(util): partition, swap, shuffle, guards.
