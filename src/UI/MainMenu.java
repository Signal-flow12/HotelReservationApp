package UI;

import api.HotelResource;


import java.sql.SQLOutput;
import java.util.Scanner;

public class MainMenu {
    public void start() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("MAIN MENU");
                    System.out.println("1. Find and Reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("----------------------------------------------- \n " +
                            "Please select a number for the menu option.");
                    int selection = Integer.parseInt(scanner.nextLine());

                    if (selection == 1) {
//                        System.out.println("Enter email");
//                        String customerEmail = scanner.nextLine().trim();
//
//                        System.out.println("Choose a room type");
//                        IRoom room = scanner.nextLine().trim();
//
//                        System.out.println("Check in date");
//                        Date checkInDate = scanner.nextLine().trim();
//
//                        System.out.println("Check out date");
//                        Date checkOutDate = scanner.nextLine().trim();
//
//                        HotelResource.getInstance().bookARoom(customerEmail, room, checkInDate, checkOutDate);
                    }
                    if (selection == 2 ) {
                        System.out.println("Enter email to see reservations");
                        String customerEmail = scanner.next().trim();

                        HotelResource.getInstance().getCustomerReservations(customerEmail);
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
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.start();
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

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
