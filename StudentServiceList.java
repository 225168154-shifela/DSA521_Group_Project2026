
public class StudentServiceList {

    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentServiceList() {
        head = null;
        size = 0;
    }

    /**
     Inserts at the beginning.
     O(1)
     */
    public void insertStudent(Student student) {
        insertAtBeginning(student);
    }

    public void insertAtBeginning(Student student) {
        validateStudent(student);

        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     Inserts at the end.
     O(n) because the list is singly linked and has no tail pointer.
     */
    public void insertAtEnd(Student student) {
        validateStudent(student);

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        size++;
    }

    /**
     Position is 1-based.
     */
    public boolean insertAtPosition(Student student, int position) {
        validateStudent(student);

        if (position < 1 || position > size + 1) {
            return false;
        }

        if (position == 1) {
            insertAtBeginning(student);
            return true;
        }

        if (position == size + 1) {
            insertAtEnd(student);
            return true;
        }

        Node newNode = new Node(student);
        Node previous = head;

        for (int i = 1; i < position - 1; i++) {
            previous = previous.next;
        }

        newNode.next = previous.next;
        previous.next = newNode;
        size++;

        return true;
    }

    /**
     Deletes the first record with the given student number.
     O(n)
     */
    public Student deleteStudent(String studentNumber) {
        if (studentNumber == null || studentNumber.trim().isEmpty()) {
            return null;
        }

        String key = studentNumber.trim();

        if (head == null) {
            return null;
        }

        if (head.data.getStudentNumber().equals(key)) {
            Student removed = head.data;
            head = head.next;
            size--;
            return removed;
        }

        Node previous = head;
        Node current = head.next;

        while (current != null) {
            if (current.data.getStudentNumber().equals(key)) {
                Student removed = current.data;
                previous.next = current.next;
                size--;
                return removed;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    /**
     Searches for a student number.
     O(n)
     */
    public Student searchStudent(String studentNumber) {
        if (studentNumber == null) {
            return null;
        }

        Node current = head;

        while (current != null) {
            if (current.data.getStudentNumber().equals(studentNumber.trim())) {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    /**
     Traverses and displays every record.
     O(n)
     */
    public void displayStudents() {
        if (head == null) {
            System.out.println("No student service records found.");
            return;
        }

        Node current = head;
        int position = 1;

        System.out.println("\n--- STUDENT SERVICE RECORDS ---");
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("Total records: " + size);
    }

    public int size() {
        return size;
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
    }
}
