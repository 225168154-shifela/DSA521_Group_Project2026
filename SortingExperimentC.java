import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    // Counters for comparison tracking
    private static long comparisons = 0;

    public static void main(String[] args) {
        runFullExperiment();
    }

    public static void runFullExperiment() {
        int[] inputSizes = {20, 50, 100, 500};
        Random random = new Random(42); // Fixed seed for reproducible test runs

        System.out.println("==========================================================================");
        System.out.println("                     PART C: SORTING EXPERIMENT RESULTS                   ");
        System.out.println("==========================================================================");
        System.out.printf("%-15s | %-10s | %-20s | %-18s\n", "Algorithm", "Input Size", "Number of Comparisons", "Execution Time (ns)");
        System.out.println("--------------------------------------------------------------------------");

        for (int size : inputSizes) {
            // Generate original test array
            int[] originalArray = new int[size];
            for (int i = 0; i < size; i++) {
                originalArray[i] = random.nextInt(1000);
            }

            // Test each sorting algorithm with an exact copy of the array
            testSelectionSort(originalArray, size);
            testInsertionSort(originalArray, size);
            testMergeSort(originalArray, size);
            testQuickSort(originalArray, size);
            System.out.println("--------------------------------------------------------------------------");
        }

        // --- Additional Test: Almost-Sorted Array (100 elements) ---
        System.out.println("\n==========================================================================");
        System.out.println("               ADDITIONAL TEST: ALMOST-SORTED ARRAY (100 ELEMENTS)        ");
        System.out.println("==========================================================================");
        
        int[] sortedArray = new int[100];
        for (int i = 0; i < 100; i++) {
            sortedArray[i] = random.nextInt(1000);
        }
        Arrays.sort(sortedArray); // First sort ascending

        // Swap 5 pairs of neighbouring values
        int[] almostSorted = Arrays.copyOf(sortedArray, sortedArray.length);
        int[] swapIndices = {10, 25, 40, 65, 80};
        for (int idx : swapIndices) {
            int temp = almostSorted[idx];
            almostSorted[idx] = almostSorted[idx + 1];
            almostSorted[idx + 1] = temp;
        }

        testSelectionSort(almostSorted, 100);
        testInsertionSort(almostSorted, 100);
        testMergeSort(almostSorted, 100);
        testQuickSort(almostSorted, 100);
        System.out.println("==========================================================================\n");
    }

    // --- Selection Sort ---
    private static void testSelectionSort(int[] original, int size) {
        int[] arr = Arrays.copyOf(original, original.length);
        comparisons = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++; // Data comparison
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.nanoTime();

        printResult("Selection Sort", size, comparisons, endTime - startTime);
    }

    // --- Insertion Sort ---
    private static void testInsertionSort(int[] original, int size) {
        int[] arr = Arrays.copyOf(original, original.length);
        comparisons = 0;

        long startTime = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++; // Data comparison
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        long endTime = System.nanoTime();

        printResult("Insertion Sort", size, comparisons, endTime - startTime);
    }

    // --- Merge Sort ---
    private static void testMergeSort(int[] original, int size) {
        int[] arr = Arrays.copyOf(original, original.length);
        comparisons = 0;

        long startTime = System.nanoTime();
        mergeSortRecursive(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();

        printResult("Merge Sort", size, comparisons, endTime - startTime);
    }

    private static void mergeSortRecursive(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortRecursive(arr, l, m);
            mergeSortRecursive(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            comparisons++; // Data comparison
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
            k++;
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // --- Quick Sort ---
    private static void testQuickSort(int[] original, int size) {
        int[] arr = Arrays.copyOf(original, original.length);
        comparisons = 0;

        long startTime = System.nanoTime();
        quickSortRecursive(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();

        printResult("Quick Sort", size, comparisons, endTime - startTime);
    }

    private static void quickSortRecursive(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortRecursive(arr, low, pi - 1);
            quickSortRecursive(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparisons++; // Data comparison
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private static void printResult(String name, int size, long comp, long time) {
        System.out.printf("%-15s | %-10d | %-20d | %-18d\n", name, size, comp, time);
    }
}
