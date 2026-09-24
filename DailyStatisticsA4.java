public class DailyStatisticsA4 {

    public static void main(String[] args) {
        // Example service times for students served during the day
        int[] serviceTimes = {12, 5, 8, 4, 15, 7, 10};

        // Variables for calculations
        int totalStudents = serviceTimes.length;
        int totalServiceTime = 0;
        int highest = serviceTimes[0];
        int lowest = serviceTimes[0];
        int longerThan10 = 0;

        // Traverse the array manually
        for (int time : serviceTimes) {
            totalServiceTime += time;

            if (time > highest) {
                highest = time;
            }
            if (time < lowest) {
                lowest = time;
            }
            if (time > 10) {
                longerThan10++;
            }
        }

        double averageServiceTime = (double) totalServiceTime / totalStudents;

        // Display results
        System.out.println("Daily Statistics:");
        System.out.println("Total students served: " + totalStudents);
        System.out.println("Total service time: " + totalServiceTime + " minutes");
        System.out.println("Average service time: " + averageServiceTime + " minutes");
        System.out.println("Highest service time: " + highest + " minutes");
        System.out.println("Lowest service time: " + lowest + " minutes");
        System.out.println("Number of services longer than 10 minutes: " + longerThan10);
    }
}
