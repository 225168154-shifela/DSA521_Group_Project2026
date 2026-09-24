/**
  Array-based storage and processing for students served during the day.
 
 */
public class DailyStatistics {

    private int[] serviceTimes;
    private int count;

    public DailyStatistics() {
        serviceTimes = new int[10];
        count = 0;
    }

    /**
     * Stores one completed service time in the array.
     * Capacity is expanded manually when necessary.
     */
    public void addServiceTime(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Service time must be greater than zero.");
        }

        if (count == serviceTimes.length) {
            int[] expanded = new int[serviceTimes.length * 2];

            for (int i = 0; i < serviceTimes.length; i++) {
                expanded[i] = serviceTimes[i];
            }

            serviceTimes = expanded;
        }

        serviceTimes[count] = minutes;
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getTotalServiceTime() {
        int total = 0;

        for (int i = 0; i < count; i++) {
            total += serviceTimes[i];
        }

        return total;
    }

    public double getAverageServiceTime() {
        if (count == 0) {
            return 0.0;
        }

        return (double) getTotalServiceTime() / count;
    }

    public int getHighestServiceTime() {
        if (count == 0) {
            return 0;
        }

        int highest = serviceTimes[0];

        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] > highest) {
                highest = serviceTimes[i];
            }
        }

        return highest;
    }

    public int getLowestServiceTime() {
        if (count == 0) {
            return 0;
        }

        int lowest = serviceTimes[0];

        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] < lowest) {
                lowest = serviceTimes[i];
            }
        }

        return lowest;
    }

    public int getServicesLongerThan10Minutes() {
        int number = 0;

        for (int i = 0; i < count; i++) {
            if (serviceTimes[i] > 10) {
                number++;
            }
        }

        return number;
    }

    /**
     Returns a manually copied array containing only the stored values.
     This is useful when another component needs the day's service times.
     */
    public int[] getServiceTimesCopy() {
        int[] copy = new int[count];

        for (int i = 0; i < count; i++) {
            copy[i] = serviceTimes[i];
        }

        return copy;
    }

    public void displayStatistics() {
        System.out.println("\n--- DAILY STATISTICS ---");

        if (count == 0) {
            System.out.println("No completed services recorded.");
            return;
        }

        System.out.println("Total students served: " + getCount());
        System.out.println("Total service time: " + getTotalServiceTime() + " min");
        System.out.printf("Average service time: %.2f min%n", getAverageServiceTime());
        System.out.println("Highest service time: " + getHighestServiceTime() + " min");
        System.out.println("Lowest service time: " + getLowestServiceTime() + " min");
        System.out.println("Services longer than 10 min: " + getServicesLongerThan10Minutes());
    }
}
