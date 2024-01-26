package UI;
import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.*;

public class AdminMenu {

    AdminResource adminResource = AdminResource.getInstance();
    public boolean start() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println( "ADMIN MENU");
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
                    if (selection == 2 ) {
                        if (adminResource != null ){
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
                } catch (Exception ex) {
                    System.out.println("\nError - Invalid Input\n");
                    ex.printStackTrace();
                }
            }

        }
        return false;
    }
    private static void addRoom (Scanner input) {
        AdminResource adminResource = AdminResource.getInstance();
        String addRoom = "y";

        while (addRoom.equals("y")) {
            RoomTypeEnum roomType = null;

            System.out.println("Enter room number: ");
            String roomNumber = input.nextLine();

            System.out.println("Enter price per night: ");
            Double price = input.nextDouble();

            while (roomType == null) {
                System.out.println("Enter room type: 1 - Single bed, 2 - Double bed");
                int type = input.nextInt();

                if (type == 1) {
                    roomType = RoomTypeEnum.SINGLE;
                } else if (type == 2) {
                    roomType = RoomTypeEnum.DOUBLE;
                } else {
                    System.out.println("Invalid input try again");
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