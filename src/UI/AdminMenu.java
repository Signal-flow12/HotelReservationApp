package UI;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomTypeEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    AdminResource adminResource = AdminResource.getInstance();

    public boolean start() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("ADMIN MENU");
                    System.out.println("1. See all customers");
                    System.out.println("2. See all rooms");
                    System.out.println("3. See all reservations");
                    System.out.println("4. Add a room");
                    System.out.println("5. Back to main menu");
                    System.out.println("----------------------------------------------- \n " +
                            "Please select a number for the menu option.");
                    int selection = Integer.parseInt(scanner.nextLine());
                    if (selection == 1) {
                        if (adminResource != null) {
                            System.out.println("All Customers:");
                            Collection<Customer> customers = adminResource.getAllCustomers();
                            for (Customer customer : customers) {
                                System.out.println(customer);
                            }
                        } else {
                            System.out.println("No customers");
                        }

                    }
                    if (selection == 2) {
                        if (adminResource != null) {
                            System.out.println("All Rooms:");
                            Collection<IRoom> rooms = adminResource.getAllRooms();
                            for (IRoom room : rooms) {
                                System.out.println(room);
                            }
                        } else {
                            System.out.println("No Rooms");
                        }
                    }
                    if (selection == 3) {
                        System.out.println("All Reservations");
                        adminResource.displayAllReservations();
                    }
                    if (selection == 4) {
                        System.out.println("Add a room");
                        addRoom(scanner);
                    }
                    if (selection == 5) {
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                }catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

        }
        return false;
    }

    private static void addRoom(Scanner input) {
        AdminResource adminResource = AdminResource.getInstance();
        String addRoom = "y";

        while (addRoom.equals("y")) {
            RoomTypeEnum roomType = null;

            System.out.println("Enter room number: ");

            String roomNumber = input.nextLine().trim();  // Change the type to String
            try {
                int roomNumberInt = Integer.parseInt(roomNumber);

                // Additional validation if needed
                if (roomNumberInt <= 0) {
                    System.out.println("Room number must be a positive integer. Please try again.");
                    continue;
                }

                if (adminResource.roomNumberExists(roomNumber)) {
                    System.out.println("Room with the same number already exists. Please enter a different room number.");
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for the room number.");
                continue;
            }

            System.out.println("Enter price per night: ");
            Double price = 0.0;

            try {
                price = Double.parseDouble(input.nextLine().trim());
                if (price <= 0) {
                    System.out.println("Price is incorrect, please try again");
                    continue;  // Add continue to go to the next iteration of the loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid price");
                continue;  // Add continue to go to the next iteration of the loop
            }

            while (roomType == null) {
                System.out.println("Enter room type: 1 - Single bed, 2 - Double bed");
                int type = input.nextInt();

                if (type == 1) {
                    roomType = RoomTypeEnum.SINGLE;
                } else if (type == 2) {
                    roomType = RoomTypeEnum.DOUBLE;
                } else {
                    System.out.println("Invalid input, try again");
                }
            }
            IRoom room = new Room(roomNumber, price, roomType);
            List<IRoom> rooms = new ArrayList<>();
            rooms.add(room);
            adminResource.addRoom(rooms);

            System.out.println("Would you like to add another room? y/n");
            input.nextLine();
            addRoom = input.nextLine().toLowerCase().trim();
        }
    }


}