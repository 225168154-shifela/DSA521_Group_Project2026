import java.util.Scanner;

public class ServiceCentreSystem {

    private final Scanner scanner;
    private final ServiceQueue waitingQueue;
    private final StudentServiceList serviceRecords;
    private final DailyStatistics dailyStatistics;

    public ServiceCentreSystem() {
        scanner = new Scanner(System.in);
        waitingQueue = new ServiceQueue();
        serviceRecords = new StudentServiceList();
        dailyStatistics = new DailyStatistics();
    }

    public void run() {
        boolean running = true;

        System.out.println("========================================");
        System.out.println("       NUST CAMPUS SERVICE CENTRE");
        System.out.println("========================================");

        while (running) {
            displayMenu();
            int option = readInt("Select option: ");

            switch (option) {
                case 1:
                    addStudentToWaitingQueue();
                    break;
                case 2:
                    serveNextStudent();
                    break;
                case 3:
                    waitingQueue.displayQueue();
                    break;
                case 4:
                    addStudentServiceRecord();
                    break;
                case 5:
                    serviceRecords.displayStudents();
                    break;
                case 6:
                    searchStudentRecord();
                    break;
                case 7:
                    removeStudentRecord();
                    break;
                case 8:
                    dailyStatistics.displayStatistics();
                    break;
                case 9:
                    sortServiceTimes();
                    break;
                case 10:
                    SortingExperiment.run();
                    break;
                case 11:
                    running = false;
                    System.out.println("Exiting Service Centre. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-11.");
            }
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("          CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
        System.out.println("========================================");
    }

    /**
     Menu option 1: Queue enqueue.
     */
    private void addStudentToWaitingQueue() {
        System.out.println("\n--- ADD STUDENT TO WAITING QUEUE ---");

        Student student = readStudent();
        waitingQueue.enqueue(student);

        System.out.println("Student added to waiting queue.");
        System.out.println("Queue size: " + waitingQueue.size());
    }

    /**
     * Menu option 2: Queue dequeue.
      When a student is served, their actual/estimated service time is also
      recorded in the daily statistics array. This is the integration point
      between Queue and Array.
     */
    private void serveNextStudent() {
        System.out.println("\n--- SERVE NEXT STUDENT ---");

        if (waitingQueue.isEmpty()) {
            System.out.println("No students are waiting.");
            return;
        }

        Student student = waitingQueue.dequeue();

        System.out.println("Now serving:");
        System.out.println(student);

        dailyStatistics.addServiceTime(student.getEstimatedServiceTime());

        System.out.println("Service completed and service time recorded.");
    }

    /**
     * Menu option 4: Linked-list insertion.
      The user chooses beginning, end, or a 1-based position.
     */
    private void addStudentServiceRecord() {
        System.out.println("\n--- ADD STUDENT SERVICE RECORD ---");
        Student student = readStudent();

        System.out.println("1. Insert at beginning");
        System.out.println("2. Insert at end");
        System.out.println("3. Insert at specified position");

        int choice = readInt("Choose insertion method: ");

        if (choice == 1) {
            serviceRecords.insertAtBeginning(student);
            System.out.println("Record inserted at beginning.");
        } else if (choice == 2) {
            serviceRecords.insertAtEnd(student);
            System.out.println("Record inserted at end.");
        } else if (choice == 3) {
            int position = readInt("Enter position (1-" + (serviceRecords.size() + 1) + "): ");

            if (serviceRecords.insertAtPosition(student, position)) {
                System.out.println("Record inserted at position " + position + ".");
            } else {
                System.out.println("Invalid position.");
            }
        } else {
            System.out.println("Invalid insertion method.");
        }
    }

    /**
      Menu option 6: Linked-list search.
     */
    private void searchStudentRecord() {
        System.out.println("\n--- SEARCH STUDENT RECORD ---");

        String studentNumber = readNonEmpty("Enter student number: ");
        Student found = serviceRecords.searchStudent(studentNumber);

        if (found == null) {
            System.out.println("Student record not found.");
        } else {
            System.out.println("Record found:");
            System.out.println(found);
        }
    }

    /**
      Menu option 7: Linked-list deletion.
     */
    private void removeStudentRecord() {
        System.out.println("\n--- REMOVE STUDENT RECORD ---");

        String studentNumber = readNonEmpty("Enter student number: ");
        Student removed = serviceRecords.deleteStudent(studentNumber);

        if (removed == null) {
            System.out.println("Student record not found.");
        } else {
            System.out.println("Removed record:");
            System.out.println(removed);
        }
    }

    /**
     * Menu option 9: Sorting component.
      A copy is sorted so that the daily statistics data remains unchanged.
     */
    private void sortServiceTimes() {
        int[] serviceTimes = dailyStatistics.getServiceTimesCopy();

        if (serviceTimes.length == 0) {
            System.out.println("No service times available. Serve students first.");
            return;
        }

        System.out.println("\n--- SORT SERVICE TIMES ---");
        displayArray(serviceTimes);

        System.out.println("1. Selection Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Quick Sort");

        int choice = readInt("Choose algorithm: ");

        SortResult result;

        if (choice == 1) {
            result = SortingAlgorithms.selectionSort(serviceTimes);
        } else if (choice == 2) {
            result = SortingAlgorithms.insertionSort(serviceTimes);
        } else if (choice == 3) {
            result = SortingAlgorithms.mergeSort(serviceTimes);
        } else if (choice == 4) {
            result = SortingAlgorithms.quickSort(serviceTimes);
        } else {
            System.out.println("Invalid algorithm.");
            return;
        }

        System.out.println("Sorted service times:");
        displayArray(serviceTimes);
        System.out.println("Data-value comparisons: " + result.getComparisons());
        System.out.println("Swaps/shifts counted by algorithm: " + result.getSwapsOrShifts());
    }

    private Student readStudent() {
        String studentNumber = readNonEmpty("Student number: ");
        String name = readNonEmpty("Student name: ");
        String serviceType = readNonEmpty("Service type: ");
        int estimatedTime = readPositiveInt("Estimated service time (minutes): ");

        return new Student(studentNumber, name, serviceType, estimatedTime);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);

            if (value > 0) {
                return value;
            }

            System.out.println("Value must be greater than zero.");
        }
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private void displayArray(int[] array) {
        System.out.print("[ ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" ]");
    }
}
