import java.util.Random;


public class SortingExperiment {

    private static final int[] SIZES = {20, 50, 100, 500};

    public static void run() {
        Random random = new Random(2026);

        System.out.println("\n================ SORTING EXPERIMENT ================");
        System.out.println("Timing measures the sorting call only.");
        System.out.println("The same original values are copied for every algorithm.");
        System.out.println();

        System.out.printf("%-18s %-8s %-15s %-18s%n",
                "Algorithm", "Size", "Comparisons", "Execution Time (ns)");
        System.out.println("---------------------------------------------------------------");

        for (int size : SIZES) {
            int[] original = generateArray(size, random);

            runOne("Selection Sort", original, 1);
            runOne("Insertion Sort", original, 2);
            runOne("Merge Sort", original, 3);
            runOne("Quick Sort", original, 4);

            System.out.println();
        }

        runAlmostSortedExperiment(random);
    }

    private static void runOne(String algorithm, int[] original, int algorithmId) {
        int[] working = copyArray(original);

        long start = System.nanoTime();
        SortResult result;

        if (algorithmId == 1) {
            result = SortingAlgorithms.selectionSort(working);
        } else if (algorithmId == 2) {
            result = SortingAlgorithms.insertionSort(working);
        } else if (algorithmId == 3) {
            result = SortingAlgorithms.mergeSort(working);
        } else {
            result = SortingAlgorithms.quickSort(working);
        }

        long end = System.nanoTime();

        System.out.printf("%-18s %-8d %-15d %-18d%n",
                algorithm, original.length,
                result.getComparisons(), end - start);
    }

    /**
     Creates an almost-sorted 100-element array:
     1. Sort the original array ascending.
     2. Swap five pairs of neighbouring values.
     */
    private static void runAlmostSortedExperiment(Random random) {
        int[] almostSorted = generateArray(100, random);

        SortingAlgorithms.mergeSort(almostSorted);

        for (int i = 0; i < 5; i++) {
            int left = i * 10;
            int right = left + 1;

            int temp = almostSorted[left];
            almostSorted[left] = almostSorted[right];
            almostSorted[right] = temp;
        }

        System.out.println("\n--- ALMOST-SORTED 100-ELEMENT ARRAY ---");

        runOne("Selection Sort", almostSorted, 1);
        runOne("Insertion Sort", almostSorted, 2);
        runOne("Merge Sort", almostSorted, 3);
        runOne("Quick Sort", almostSorted, 4);
    }

    private static int[] generateArray(int size, Random random) {
        int[] values = new int[size];

        for (int i = 0; i < size; i++) {
            values[i] = 1 + random.nextInt(1000);
        }

        return values;
    }

    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        return copy;
    }
}
