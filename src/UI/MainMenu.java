package UI;

import com.sun.tools.javac.Main;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("1. Find and Reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an accoint");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("----------------------------------------------- \n " +
                            "Please select a number for the menu option.");
                    int selection = Integer.parseInt(scanner.nextLine());

                    if (selection == 1) {
                        System.out.println("1");
                        keepRunning = false;
                    }
                    if (selection == 2 ) {
                        System.out.println("2");
                        keepRunning = false;
                    }
                    if (selection == 3) {
                        System.out.println("3");
                        keepRunning = false;
                    }
                    if (selection == 4) {
                        System.out.println("4");
                        keepRunning = false;
                    }
                    if (selection == 5) {
                        System.out.println("5");
                        keepRunning = false;
                    }
                } catch (Exception ex) {
                    System.out.println("\nError - Invalid Input\n");
                }
            }

        }
    }


}
