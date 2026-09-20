import csv
import math
import matplotlib.pyplot as plt

data = []

with open("results.csv", "r") as file:
    reader = csv.DictReader(file)

    for row in reader:
        row["n"] = int(row["n"])
        row["time_ms"] = float(row["time_ms"])
        row["comparisons"] = int(row["comparisons"])
        row["max_depth"] = int(row["max_depth"])

        data.append(row)


algorithms = ["MergeSort", "QuickSort", "QuickSelect"]
inputs = ["random", "sorted", "duplicates"]


# GRAPH 1: Time vs n

for algorithm in algorithms:
    for input_type in inputs:

        rows = [
            row for row in data
            if row["algorithm"] == algorithm
            and row["input"] == input_type
        ]

        x = [row["n"] for row in rows]
        y = [row["time_ms"] for row in rows]

        plt.plot(
            x,
            y,
            marker="o",
            label=algorithm + " - " + input_type
        )

plt.xscale("log")

plt.xlabel("Input size n")
plt.ylabel("Time (ms)")
plt.title("Running Time vs Input Size")
plt.legend(fontsize=7)

plt.tight_layout()
plt.savefig("time_vs_n.png", dpi=300)
plt.close()


# GRAPH 2: Maximum recursion depth

for algorithm in algorithms:
    for input_type in inputs:

        rows = [
            row for row in data
            if row["algorithm"] == algorithm
            and row["input"] == input_type
        ]

        x = [row["n"] for row in rows]
        y = [row["max_depth"] for row in rows]

        plt.plot(
            x,
            y,
            marker="o",
            label=algorithm + " - " + input_type
        )

plt.xscale("log")

plt.xlabel("Input size n")
plt.ylabel("Maximum recursion depth")
plt.title("Maximum Recursion Depth vs Input Size")
plt.legend(fontsize=7)

plt.tight_layout()
plt.savefig("max_depth_vs_n.png", dpi=300)
plt.close()


# GRAPH 3: Comparison ratios

for algorithm in algorithms:
    for input_type in inputs:

        rows = [
            row for row in data
            if row["algorithm"] == algorithm
            and row["input"] == input_type
        ]

        x = []
        y = []

        for row in rows:

            n = row["n"]
            comparisons = row["comparisons"]

            if algorithm == "QuickSelect":
                ratio = comparisons / n
            else:
                ratio = comparisons / (n * math.log2(n))

            x.append(n)
            y.append(ratio)

        plt.plot(
            x,
            y,
            marker="o",
            label=algorithm + " - " + input_type
        )

plt.xscale("log")

plt.xlabel("Input size n")
plt.ylabel("Comparison ratio")
plt.title("Normalized Number of Comparisons")
plt.legend(fontsize=7)

plt.tight_layout()
plt.savefig("comparison_ratios.png", dpi=300)
plt.close()


print("Plots created successfully")