import java.io.*;
import java.util.*;

class Student implements Serializable {
    private int rollNo;
    private String name;
    private String grade;
    private int age;

    public Student(int rollNo, String name, String grade, int age) {
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
               "Roll No : " + rollNo +
               "\nName    : " + name +
               "\nGrade   : " + grade +
               "\nAge     : " + age +
               "\n----------------------------------------";
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {

        loadFromFile();

        int choice;

        do {
            System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    removeStudent();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    displayStudents();
                    break;

                case 5:
                    editStudent();
                    break;

                case 6:
                    saveToFile();
                    break;

                case 7:
                    saveToFile();
                    System.out.println("Data Saved.");
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);
    }

    // Add Student
    static void addStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student existing = findStudent(roll);
        if (existing != null) {
            System.out.println("Roll Number already exists!");
            return;
        }

        String name;
        do {
            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty())
                System.out.println("Name cannot be empty.");
        } while (name.isEmpty());

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        int age;
        do {
            System.out.print("Enter Age: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid age: ");
                sc.next();
            }
            age = sc.nextInt();
        } while (age <= 0);

        students.add(new Student(roll, name, grade, age));

        System.out.println("Student Added Successfully.");
    }

    // Remove Student
    static void removeStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        Student s = findStudent(roll);

        if (s != null) {
            students.remove(s);
            System.out.println("Student Removed Successfully.");
        } else {
            System.out.println("Student Not Found.");
        }
    }

    // Search Student
    static void searchStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        Student s = findStudent(roll);

        if (s != null)
            System.out.println(s);
        else
            System.out.println("Student Not Found.");
    }

    // Display Students
    static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Found.");
            return;
        }

        System.out.println("\n===== Student Records =====");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Edit Student
    static void editStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        Student s = findStudent(roll);

        if (s == null) {
            System.out.println("Student Not Found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Grade: ");
        String grade = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        s.setName(name);
        s.setGrade(grade);
        s.setAge(age);

        System.out.println("Student Updated Successfully.");
    }

    // Find Student
    static Student findStudent(int roll) {

        for (Student s : students) {
            if (s.getRollNo() == roll)
                return s;
        }

        return null;
    }

    // Save Data
    static void saveToFile() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(students);
            out.close();
            System.out.println("Data Saved Successfully.");
        } catch (IOException e) {
            System.out.println("Error Saving File.");
        }
    }

    // Load Data
    @SuppressWarnings("unchecked")
    static void loadFromFile() {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            students = (ArrayList<Student>) in.readObject();
            in.close();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}