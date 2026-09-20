# DAA Assignment 1

## Description

This project is Assignment 1 for Design and Analysis of Algorithms.

The project implements and compares three algorithms:

- MergeSort
- QuickSort
- QuickSelect

The program also collects performance metrics such as execution time, number of comparisons, and maximum recursion depth.

## Algorithms

### MergeSort
MergeSort uses one reusable temporary buffer.  
For small subarrays with size 15 or less, Insertion Sort is used.

### QuickSort
QuickSort uses a random pivot and 3-way partitioning.  
The smaller partition is processed recursively and the larger partition is processed using a loop to control recursion depth.

### QuickSelect
QuickSelect finds the element with index `k`, where `k` is 0-based.  
It uses the same 3-way partition as QuickSort and continues only with the required part of the array.

## Project Structure

- `MergeSort.java` - MergeSort implementation
- `QuickSort.java` - QuickSort implementation
- `QuickSelect.java` - QuickSelect implementation
- `Metrics.java` - stores comparisons, execution time and recursion depth
- `Benchmark.java` - runs benchmark experiments
- `results.csv` - benchmark results
- `plots.py` - creates plots from results.csv
- `time_vs_n.png` - execution time plot
- `max_depth_vs_n.png` - recursion depth plot
- `comparison_ratios.png` - normalized comparisons plot

## Testing

JUnit 5 is used for testing.

The tests compare sorting results with `Arrays.sort()` and QuickSelect results with the expected element from a sorted array.

The project contains tests for:

- random arrays
- empty arrays
- one-element arrays
- arrays with equal values
- already sorted arrays
- QuickSort recursion depth
- QuickSelect invalid input

## Benchmark

The benchmark uses four input sizes:

`1000, 10000, 100000, 1000000`

Three input types are tested:

- random
- sorted
- duplicates

Each experiment is executed 5 times and the median execution time is saved.

Run `Benchmark.java` to generate:

`results.csv`

## Generate Plots

The plots are generated with Python and Matplotlib.

Activate the virtual environment:

```powershell
.\.venv\Scripts\Activate.ps1