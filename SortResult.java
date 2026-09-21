/**
 Holds the result of a sorting operation.
 */
public class SortResult {
    private final int comparisons;
    private final int swapsOrShifts;

    public SortResult(int comparisons, int swapsOrShifts) {
        this.comparisons = comparisons;
        this.swapsOrShifts = swapsOrShifts;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwapsOrShifts() {
        return swapsOrShifts;
    }
}
