package UI;

import api.HotelResource;
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
                    System.out.println("3. Create an account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("----------------------------------------------- \n " +
                            "Please select a number for the menu option.");
                    int selection = Integer.parseInt(scanner.nextLine());

                    if (selection == 1) {
                        System.out.println("1");
                    }
                    if (selection == 2 ) {
                        System.out.println("2");
                    }
                    if (selection == 3) {

                        System.out.println("Enter first name");
                        String firstName = scanner.nextLine();

                        System.out.println("Enter last name");
                        String lastName = scanner.nextLine().trim();

                        System.out.println("Enter Customer Email");
                        String email = scanner.nextLine().trim();

                        HotelResource.getInstance().createACustomer(firstName, lastName, email);
                        System.out.println("Customer created successfully");
                    }
                    if (selection == 4) {
                        System.out.println("4");
                    }
                    if (selection == 5) {
                        System.out.println("5");
                        keepRunning = false;
                    }
                } catch (Exception ex) {
                    System.out.println("\nError - Invalid Input\n");
                    ex.printStackTrace();
                }
            }

        }
    }


}
