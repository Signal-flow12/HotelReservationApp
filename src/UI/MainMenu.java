package UI;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
                        findAndReserveRoom(scanner);
                    }
                    if (selection == 2) {
                        getCustomerReservation(scanner);
                    }
                    if (selection == 3) {
                        createAccount(scanner);
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

    public void findAndReserveRoom(Scanner scanner) {

        HotelResource hotelResource = HotelResource.getInstance();
        //find a room based on check in and checkout dates (findARoom)
        System.out.println("CheckIn Date (MM--dd-yyyy):");
        String checkInDateString = scanner.nextLine();

        System.out.println("CheckOut Date (MM--dd-yyyy):");
        String checkOutDateString = scanner.nextLine();

        //parse date to strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM--dd-yyyy");
        try {
            Date chechInDate = dateFormat.parse(checkInDateString);
            Date checkOutDate = dateFormat.parse(checkOutDateString);

            //find  available rooms
            Collection<IRoom> availableRooms = hotelResource.findARoom(chechInDate, checkOutDate);

            //Display rooms
            System.out.println("Available Rooms");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }

            //ask the user to choose a room
            System.out.println("Please enter room number to reserve a room or press 0 to cancel");
            String roomNumber = scanner.nextLine();

            if (!roomNumber.equals("0")) {
                System.out.println("Enter customer email");
                String customerEmail = scanner.nextLine();

                IRoom selectedRoom = hotelResource.getRoom(roomNumber);

                Reservation reservation = hotelResource.bookARoom(customerEmail, selectedRoom, chechInDate, checkOutDate);

                System.out.println("Room reserved successfully, see details below.");
                System.out.println(reservation);

            }


        } catch (Exception e) {
            System.out.println("Invalid date format, please try again");
        }
    }

    public void getCustomerReservation(Scanner scanner) {

        HotelResource hotelResource = HotelResource.getInstance();

        System.out.println("Enter customer email");
        String customerEmail = scanner.nextLine();

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(customerEmail);

        if (reservations.isEmpty()) {
            System.out.println("No reservation found for this customer");
        } else {
            System.out.println("Your reservations: ");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public void createAccount(Scanner scanner) {

        System.out.println("Enter first name");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name");
        String lastName = scanner.nextLine().trim();

        System.out.println("Enter Customer Email");
        String email = scanner.nextLine().trim();

        HotelResource.getInstance().createACustomer(firstName, lastName, email);
        System.out.println("Customer created successfully");
    }

}
