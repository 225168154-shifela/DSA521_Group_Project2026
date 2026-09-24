public class Student {
    int studentNo;
    String name;
    String serviceType;
    int serviceTime;

    Student(int studentNo, String name, String serviceType, int serviceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.serviceTime = serviceTime;
    }

    public String toString() {
        return "Student No: " + studentNo + ", Name: " + name + ", Service Type: " + serviceType + ", Service Time: " + serviceTime;
    }
}