package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    public static AdminResource adminResource;
    private static CustomerService customerService = new CustomerService();
    private static ReservationService reservationService = ReservationService.getInstance();
    private AdminResource() {
    }
    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservations();
    }

    public boolean roomNumberExists(String roomNumber) {
        for (IRoom existingRoom : getAllRooms()) {
            if (existingRoom.getRoomNumber().equals(roomNumber)) {
                return true;
            }
        }
        return false;
    }

}
