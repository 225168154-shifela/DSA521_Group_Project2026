
public class ServiceQueue {

    private static class QueueNode {
        Student data;
        QueueNode next;

        QueueNode(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public ServiceQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
      Adds a student to the rear of the queue.
      Time complexity: O(1)
     */
    public void enqueue(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot enqueue a null student.");
        }

        QueueNode newNode = new QueueNode(student);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    /**
      Removes and returns the student at the front.
      Time complexity: O(1)
     */
    public Student dequeue() {
        if (isEmpty()) {
            return null;
        }

        Student served = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return served;
    }

    /**
      Returns, but does not remove, the next student.
      Time complexity: O(1)
     */
    public Student peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    /**
      Displays the queue from front to rear.
      Time complexity: O(n)
     */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Waiting queue is empty.");
            return;
        }

        QueueNode current = front;
        int position = 1;

        System.out.println("\n--- WAITING QUEUE (FRONT -> REAR) ---");
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("Total waiting: " + size);
    }
}
