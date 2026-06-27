import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    static final int LOWER_LIMIT = 1;
    static final int UPPER_LIMIT = 100;
    static final int MAX_CHANCES = 8;

    static int gamesPlayed = 0;
    static int gamesWon = 0;
    static int totalScore = 0;

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("     WELCOME TO NUMBER QUEST");
        System.out.println("==================================");

        boolean playAgain = true;

        while (playAgain) {
            playGame();
            playAgain = askReplay();
        }

        System.out.println("\n========== FINAL RESULT ==========");
        System.out.println("Games Played : " + gamesPlayed);
        System.out.println("Games Won    : " + gamesWon);
        System.out.println("Final Score  : " + totalScore);
        System.out.println("Thank you for playing!");
    }

    static void playGame() {

        gamesPlayed++;

        int secret = random.nextInt(UPPER_LIMIT) + LOWER_LIMIT;
        int chance = 1;
        boolean win = false;

        System.out.println("\nRound " + gamesPlayed);
        System.out.println("----------------------------------");
        System.out.println("Guess a number between "
                + LOWER_LIMIT + " and " + UPPER_LIMIT);

        while (chance <= MAX_CHANCES) {

            System.out.print("Attempt " + chance + "/" + MAX_CHANCES + ": ");

            while (!input.hasNextInt()) {
                System.out.print("Enter numbers only: ");
                input.next();
            }

            int guess = input.nextInt();

            if (guess == secret) {
                win = true;
                gamesWon++;

                int earnedPoints = (MAX_CHANCES - chance + 1) * 10;
                totalScore += earnedPoints;

                System.out.println("\nCorrect!");
                System.out.println("You guessed the number in " + chance + " attempts.");
                System.out.println("Points Earned : " + earnedPoints);
                break;
            }

            int difference = Math.abs(secret - guess);

            if (guess > secret) {
                System.out.print("Too High");
            } else {
                System.out.print("Too Low");
            }

            if (difference <= 5) {
                System.out.println(" (Very Close!)");
            } else if (difference <= 15) {
                System.out.println(" (Close)");
            } else {
                System.out.println();
            }

            chance++;
        }

        if (!win) {
            System.out.println("\nNo more chances!");
            System.out.println("The correct number was: " + secret);
        }

        System.out.println("\nCurrent Statistics");
        System.out.println("------------------");
        System.out.println("Games Played : " + gamesPlayed);
        System.out.println("Games Won    : " + gamesWon);
        System.out.println("Score        : " + totalScore);
    }

    static boolean askReplay() {

        System.out.print("\nDo you want another round? (Y/N): ");
        String answer = input.next();

        return answer.equalsIgnoreCase("Y");
    }
}