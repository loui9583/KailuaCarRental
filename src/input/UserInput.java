package input;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner sc2 = new Scanner(System.in);

    public static int getInt() {
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again!");
                sc.nextLine();
            }
        }
        return num;
    }

    public static String getString() {
        return sc2.nextLine();
    }

    public static LocalDate buildDate(String type) {
        LocalDate date = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter " + type + " year:");
                int year = getInt();
                System.out.println("Enter " + type + " month:");
                int month = getInt();
                System.out.println("Enter " + type + " day:");
                int day = getInt();
                date = LocalDate.of(year, month, day);
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid date. Please try again.");
            }
        }
    return date;
    }
}
