import java.util.Scanner;

public class GradeAnalyzer {

    // Method to return grade
    static String findGrade(double average) {
        if (average >= 90)
            return "A+";
        else if (average >= 80)
            return "A";
        else if (average >= 70)
            return "B";
        else if (average >= 60)
            return "C";
        else if (average >= 50)
            return "D";
        else
            return "F";
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("====== Student Grade Calculator ======");

        System.out.print("How many subjects? ");
        int numberOfSubjects = input.nextInt();

        int[] marks = new int[numberOfSubjects];
        int total = 0;

        // Reading marks
        for (int i = 0; i < numberOfSubjects; i++) {

            while (true) {
                System.out.print("Marks for Subject " + (i + 1) + ": ");
                marks[i] = input.nextInt();

                if (marks[i] >= 0 && marks[i] <= 100) {
                    break;
                }

                System.out.println("Please enter marks between 0 and 100.");
            }

            total += marks[i];
        }

        double average = (double) total / numberOfSubjects;
        String grade = findGrade(average);

        System.out.println("\n========== RESULT ==========");
        System.out.println("Subjects           : " + numberOfSubjects);
        System.out.println("Total Marks        : " + total + "/" + (numberOfSubjects * 100));
        System.out.printf("Average Percentage : %.2f%%\n", average);
        System.out.println("Final Grade        : " + grade);

        if (grade.equals("A+") || grade.equals("A")) {
            System.out.println("Remark             : Excellent Performance!");
        } else if (grade.equals("B") || grade.equals("C")) {
            System.out.println("Remark             : Good Job, Keep Improving!");
        } else if (grade.equals("D")) {
            System.out.println("Remark             : You Passed.");
        } else {
            System.out.println("Remark             : Better Luck Next Time.");
        }

        input.close();
    }
}