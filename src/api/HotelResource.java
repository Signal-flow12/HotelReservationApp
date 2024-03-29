package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static HotelResource hotelResource;

    private static CustomerService customerService = new CustomerService();
    private static ReservationService reservationService = ReservationService.getInstance();

    // Private constructor to prevent external instantiation
    private HotelResource() {
    }

    // Static method to get the single instance of HotelResource
    public static HotelResource getInstance() {
        if (hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }
    public static Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail){
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomerReservation(customer);
    }


    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }

}
