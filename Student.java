public class Student {
    private final String studentNumber;
    private final String name;
    private final String serviceType;
    private final int estimatedServiceTime;

    public Student(String studentNumber, String name, String serviceType, int estimatedServiceTime) {
        if (studentNumber == null || studentNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Student number cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        if (serviceType == null || serviceType.trim().isEmpty()) {
            throw new IllegalArgumentException("Service type cannot be empty.");
        }
        if (estimatedServiceTime <= 0) {
            throw new IllegalArgumentException("Service time must be greater than zero.");
        }

        this.studentNumber = studentNumber.trim();
        this.name = name.trim();
        this.serviceType = serviceType.trim();
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getEstimatedServiceTime() {
        return estimatedServiceTime;
    }

    @Override
    public String toString() {
        return "Student No: " + studentNumber
                + " | Name: " + name
                + " | Service: " + serviceType
                + " | Estimated Time: " + estimatedServiceTime + " min";
    }
}
