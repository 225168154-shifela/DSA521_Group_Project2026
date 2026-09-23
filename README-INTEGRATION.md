# NUST Service Centre — Integrated System

## Purpose

This module is the integration layer for the DSA521S Group Mini-Project.

It connects:

- Queue — waiting students
- Singly Linked List — student service records
- Array — daily service statistics
- Selection Sort
- Insertion Sort
- Merge Sort
- Quick Sort
- Sorting experiment

The postfix Stack exercise is intentionally not included in the integrated menu because the project brief states that A3 is a separate exercise.

## Files

- `Student.java` — common student data model
- `ServiceQueue.java` — custom FIFO queue
- `StudentServiceList.java` — custom singly linked list
- `DailyStatistics.java` — array-based daily statistics
- `SortResult.java` — sorting result/counter object
- `SortingAlgorithms.java` — four required sorting algorithms
- `SortingExperiment.java` — Part C experiment
- `ServiceCentreSystem.java` — integration/menu layer
- `Main.java` — program entry point

## Compile

```text
javac *.java
```

## Run

```text
java Main
```

## Integration contract for the group

If another group member already has an implementation, do NOT blindly overwrite it.

The integration layer expects these concepts:

### Student

A student must expose:

- `getStudentNumber()`
- `getName()`
- `getServiceType()`
- `getEstimatedServiceTime()`

### Queue

The service-centre queue must provide:

- `enqueue(Student)`
- `dequeue()`
- `peek()`
- `isEmpty()`
- `displayQueue()`

### Linked List

The service-record list must provide:

- `insertStudent(Student)`
- `insertAtBeginning(Student)`
- `insertAtEnd(Student)`
- `insertAtPosition(Student, int)`
- `deleteStudent(String)`
- `searchStudent(String)`
- `displayStudents()`

### Daily Statistics

The array component must provide:

- add service time
- total students
- total service time
- average
- highest
- lowest
- number longer than 10 minutes
- copy of stored service times for sorting

### Sorting

Each sorting method should return its comparison count. The experiment must:

1. generate one original array;
2. give each algorithm a copy of the same values;
3. time only the sorting call;
4. count data-value comparisons only.

## Recommended Git workflow

Create a branch for the integration work:

```text
git checkout -b feature/integrated-service-centre
```

Commit logically:

```text
git add Student.java ServiceQueue.java StudentServiceList.java
git commit -m "Add service centre data structure integration"

git add DailyStatistics.java SortingAlgorithms.java SortResult.java
git commit -m "Integrate statistics and sorting components"

git add SortingExperiment.java ServiceCentreSystem.java Main.java
git commit -m "Add integrated service centre menu and experiment"

git add README-INTEGRATION.md
git commit -m "Document service centre integration contract"
```

Then push:

```text
git push -u origin feature/integrated-service-centre
```

Create a Pull Request so the group can review the code before merging.

## Important academic requirement

Every group member must understand the submitted code. The project brief explicitly says that the lecturer may ask any member to explain the source code, algorithms, pseudocode, traces, and design decisions.

This code should therefore be treated as a development scaffold to study, test, adapt, and explain rather than as code to submit without understanding.
