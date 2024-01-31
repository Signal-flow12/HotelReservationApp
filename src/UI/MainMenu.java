package UI;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    public void start() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("MAIN MENU \nPress Q at anytime to return to main menu" );
                    System.out.println("-----------------------------------------------");
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
                    } else if (selection == 2) {
                        getCustomerReservation(scanner);
                    } else if (selection == 3) {
                        createAccount(scanner);
                    } else if (selection == 4) {
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.start();
                    } else if (selection == 5) {
                        System.out.println("5");
                        keepRunning = false;
                    } else {
                        System.out.println("Not a valid number please try again");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
    }

    public void findAndReserveRoom(Scanner scanner) {
        HotelResource hotelResource = HotelResource.getInstance();
        AdminResource adminResource = AdminResource.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM--dd-yyyy");

        Date checkInDate = null;
        Date checkOutDate = null;

        // Validate and get the correct format for CheckIn Date
        while (checkInDate == null) {
            System.out.println("CheckIn Date (MM--dd-yyyy):");
            String checkInDateString = scanner.nextLine();

            //return to main menu
            if (checkInDateString.equalsIgnoreCase("q")) {
                return;
            }

            try {
                checkInDate = dateFormat.parse(checkInDateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in MM--dd-yyyy format.");
            }
        }

        // Validate and get the correct format for CheckOut Date
        while (checkOutDate == null) {
            System.out.println("CheckOut Date (MM--dd-yyyy):");
            String checkOutDateString = scanner.nextLine();

            if (checkOutDateString.equalsIgnoreCase("q")) {
                return;
            }

            try {
                checkOutDate = dateFormat.parse(checkOutDateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in MM--dd-yyyy format.");
            }
        }

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if (availableRooms.isEmpty()) {
            // No available rooms for the specified date range, try alternative dates
            System.out.println("No available rooms for the specified dates. Searching for alternative dates...");

            // Try searching for alternative dates by adding seven days to the original dates
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkInDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date alternativeCheckInDate = calendar.getTime();

            calendar.setTime(checkOutDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date alternativeCheckOutDate = calendar.getTime();

            Collection<IRoom> alternativeRooms = hotelResource.findARoom(alternativeCheckInDate, alternativeCheckOutDate);

            if (!alternativeRooms.isEmpty()) {
                // Display recommended rooms on alternative dates
                System.out.println("Recommended rooms on alternative dates:");

                for (IRoom room : alternativeRooms) {
                    System.out.println(room);
                }
            } else {
                System.out.println("No available rooms for the alternative dates as well. Please try different dates.");
                return; // Exit the method as no rooms are available for the alternative dates
            }
        } else {
            // Display available rooms for the original dates
            System.out.println("Available Rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }
        }

        String roomNumber = null;

        while (roomNumber == null) {
            // Ask the user to choose a room

            System.out.println("Please enter room number to reserve a room or press q to cancel");
            roomNumber = scanner.nextLine();

            if (roomNumber.equalsIgnoreCase("q")) {
                return;
            }

            boolean validRoomNumber = false;

            for (IRoom room : availableRooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    validRoomNumber = true;
                    break;
                }
            }

            if (!validRoomNumber) {
                System.out.println("Invalid room number. Please enter a valid room number.");
                roomNumber = null; // Reset roomNumber to continue the loop
            } else {

                String customerEmail = null;
                // The entered room number is valid
                while (customerEmail == null) {
                    System.out.println("Enter customer email");
                    customerEmail = scanner.nextLine();

                    if (customerEmail.equalsIgnoreCase("q")) {
                        return;
                    }

                    Collection<Customer> customerEmails = adminResource.getAllCustomers();

                    boolean validCustomerEmail = false;

                    for (Customer customer : customerEmails) {
                        if (customer.getEmail().equalsIgnoreCase(customerEmail)) {
                            validCustomerEmail = true;
                            break;
                        }
                    }

                    if (!validCustomerEmail) {
                        System.out.println("Invalid customer email. Please enter a valid email.");
                        customerEmail = null; // Reset customerEmail to continue the loop
                    } else {
                        IRoom selectedRoom = hotelResource.getRoom(roomNumber);

                        Reservation reservation = hotelResource.bookARoom(customerEmail, selectedRoom, checkInDate, checkOutDate);

                        System.out.println("Room reserved successfully, see details below.");
                        System.out.println(reservation);
                    }
                }
            }
        }
    }



    public void getCustomerReservation(Scanner scanner) {

        HotelResource hotelResource = HotelResource.getInstance();
        AdminResource adminResource = AdminResource.getInstance();

        String customerEmail = null;

        while (customerEmail == null) {
            System.out.println("Enter customer email");
            customerEmail = scanner.nextLine();

            if (customerEmail.equalsIgnoreCase("q")) {
                return;
            }
            Collection<Customer> customerEmails = adminResource.getAllCustomers();
            boolean validCustomerEmail = false;

            for (Customer customer : customerEmails) {
                if (customer.getEmail().equalsIgnoreCase(customerEmail)) {
                    validCustomerEmail = true;
                    break;
                }
            }

            if (!validCustomerEmail) {
                System.out.println("Cannot find customer email, please try again");
                customerEmail = null;
            } else {

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
        }
    }

    public void createAccount(Scanner scanner) {

        System.out.println("Enter first name");
        String firstName = scanner.nextLine();

        if(firstName.equalsIgnoreCase("q")){
            return;
        }

        System.out.println("Enter last name");
        String lastName = scanner.nextLine().trim();

        if(lastName.equalsIgnoreCase("q")){
            return;
        }

        String email;
        while (true) {
            System.out.println("Enter Customer Email");
            email = scanner.nextLine().trim();

            if(email.equalsIgnoreCase("q")){
                return;
            }

            String errorMessage = Customer.validateEmail(email);
            if (errorMessage == null) {
                break; // Email is valid, exit the loop
            } else {
                System.out.println(errorMessage);
            }
        }


        HotelResource.getInstance().createACustomer(firstName, lastName, email);
        System.out.println("Customer created successfully");
    }

}
