
public class SortingAlgorithms {

    /**
     Selection Sort.
     Counts data comparisons and swaps.
     Time: O(n^2)
     */
    public static SortResult selectionSort(int[] array) {
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                comparisons++;

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(array, i, minIndex);
                swaps++;
            }
        }

        return new SortResult(comparisons, swaps);
    }

    /**
     Insertion Sort.
     Counts data comparisons and shifts.
     Time: O(n^2) worst case, O(n) best case.
     */
    public static SortResult insertionSort(int[] array) {
        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;

                if (array[j] > key) {
                    array[j + 1] = array[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }

            array[j + 1] = key;
        }

        return new SortResult(comparisons, shifts);
    }

    /**
     Merge Sort.
     Counts comparisons between values during merge operations.
     Time: O(n log n)
     */
    public static SortResult mergeSort(int[] array) {
        if (array.length <= 1) {
            return new SortResult(0, 0);
        }

        int[] temp = new int[array.length];
        Counter counter = new Counter();

        mergeSortRecursive(array, temp, 0, array.length - 1, counter);

        return new SortResult(counter.comparisons, 0);
    }

    private static void mergeSortRecursive(
            int[] array, int[] temp, int left, int right, Counter counter) {

        if (left >= right) {
            return; // base case
        }

        int middle = left + (right - left) / 2;

        mergeSortRecursive(array, temp, left, middle, counter);
        mergeSortRecursive(array, temp, middle + 1, right, counter);

        merge(array, temp, left, middle, right, counter);
    }

    private static void merge(
            int[] array, int[] temp,
            int left, int middle, int right, Counter counter) {

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            counter.comparisons++;

            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }

            k++;
        }

        while (i <= middle) {
            temp[k] = array[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = array[j];
            j++;
            k++;
        }

        for (int index = left; index <= right; index++) {
            array[index] = temp[index];
        }
    }

    /**
     Quick Sort using the last element as the pivot.
     Counts data comparisons in partitioning.
     Average: O(n log n); worst case: O(n^2).
     */
    public static SortResult quickSort(int[] array) {
        Counter counter = new Counter();
        quickSortRecursive(array, 0, array.length - 1, counter);
        return new SortResult(counter.comparisons, 0);
    }

    private static void quickSortRecursive(
            int[] array, int low, int high, Counter counter) {

        if (low < high) {
            int pivotIndex = partition(array, low, high, counter);

            quickSortRecursive(array, low, pivotIndex - 1, counter);
            quickSortRecursive(array, pivotIndex + 1, high, counter);
        }
    }

    private static int partition(
            int[] array, int low, int high, Counter counter) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            counter.comparisons++;

            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static class Counter {
        int comparisons = 0;
    }
}
