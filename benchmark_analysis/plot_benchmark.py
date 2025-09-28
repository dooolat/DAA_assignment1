import pandas as pd
import matplotlib.pyplot as plt

# Read benchmark results
df = pd.read_csv("../results.csv", decimal=',')  # decimal=',' for correct numbers

# Extract times for each algorithm
n_values = df["Param: n"].unique()
merge_times = df[df["Benchmark"].str.contains("benchmergesort")]["Score"].values
quick_times = df[df["Benchmark"].str.contains("benchquicksort")]["Score"].values
select_times = df[df["Benchmark"].str.contains("benchselect")]["Score"].values

# Create plot
plt.figure(figsize=(10,6))
plt.plot(n_values, merge_times, marker='o', linestyle='-', color='blue', label='MergeSort')
plt.plot(n_values, quick_times, marker='s', linestyle='--', color='green', label='QuickSort')
plt.plot(n_values, select_times, marker='^', linestyle='-.', color='red', label='Select')

# Labels and title
plt.xlabel('Array Size n')
plt.ylabel('Time (ms/op)')
plt.title('Benchmark: MergeSort vs QuickSort vs Select')
plt.xticks(n_values)
plt.grid(True, linestyle='--', alpha=0.7)
plt.legend()
plt.tight_layout()

# Save figure
plt.savefig("benchmark_plot.png", dpi=300)
plt.show()
