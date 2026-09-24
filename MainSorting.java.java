import java.util.Arrays;

public class Main {

    static long comparisons = 0;
    static long swaps = 0;
    static long shifts = 0;

    // B1 - Selection Sort
    static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < array.length; j++) {
                comparisons++;

                if (array[j] < array[min]) {
                    min = j;
                }
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
            swaps++;

            if (i < 3) {
                System.out.println("pass " + (i + 1) + ": "
                        + Arrays.toString(array));
            }
        }
    }

    // B2 - Insertion Sort
    static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;

                if (array[j] > temp) {
                    array[j + 1] = array[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }

            array[j + 1] = temp;

            if (i <= 3) {
                System.out.println("pass " + i + ": "
                        + Arrays.toString(array));
            }
        }
    }

    // B3 - Merge Sort
    static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[array.length];

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            comparisons++;

            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (k = left; k <= right; k++) {
            array[k] = temp[k];
        }
    }

    // B4 - Quick Sort
    static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivot = start;
            int pivotValue = array[pivot];

            int i = start;
            int j = end;

            while (i < j) {
                while (i < end) {
                    comparisons++;

                    if (array[i] <= pivotValue) {
                        i++;
                    } else {
                        break;
                    }
                }

                while (j > start) {
                    comparisons++;

                    if (array[j] > pivotValue) {
                        j--;
                    } else {
                        break;
                    }
                }

                if (i < j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    swaps++;
                }
            }

            int temp = array[pivot];
            array[pivot] = array[j];
            array[j] = temp;
            swaps++;

            quickSort(array, start, j - 1);
            quickSort(array, j + 1, end);
        }
    }

    static int[] original() {
        return new int[]{17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
    }

    public static void main(String[] args) {

        // B1
        int[] a = original();
        comparisons = 0;
        swaps = 0;

        System.out.println("B1 - Selection Sort");
        selectionSort(a);
        System.out.println("Sorted: " + Arrays.toString(a));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);

        // B2
        a = original();
        comparisons = 0;
        shifts = 0;

        System.out.println("\nB2 - Insertion Sort");
        insertionSort(a);
        System.out.println("Sorted: " + Arrays.toString(a));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Shifts: " + shifts);

        // B3
        a = original();
        comparisons = 0;

        System.out.println("\nB3 - Merge Sort");
        mergeSort(a, 0, a.length - 1);
        System.out.println("Sorted: " + Arrays.toString(a));
        System.out.println("Comparisons: " + comparisons);

        // B4
        a = original();
        comparisons = 0;
        swaps = 0;

        System.out.println("\nB4 - Quick Sort");
        System.out.println("Pivot rule: First element");
        quickSort(a, 0, a.length - 1);
        System.out.println("Sorted: " + Arrays.toString(a));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
    }
}
