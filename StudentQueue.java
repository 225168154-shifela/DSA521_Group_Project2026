class StudentQueue {

    private StudentNode front;
    private StudentNode rear;

    private class StudentNode {
        Student data;
        StudentNode next;

        StudentNode(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(Student student) {

        StudentNode newNode = new StudentNode(student);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Student dequeue() {

        if (isEmpty()) {
            System.out.println("This queue is empty.");
            return null;
        }

        Student servedStudent = front.data;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return servedStudent;
    }

    public Student peek() {

        if (isEmpty()) {
            return null;
        }

        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("This queue is empty.");
            return;
        }

        StudentNode current = front;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}