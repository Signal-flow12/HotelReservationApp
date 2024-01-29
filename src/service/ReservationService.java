package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService reservationService;
    public Set<Reservation> reservations = new HashSet<>();
    public List<IRoom> rooms = new ArrayList<>();

    private ReservationService() {
    }

    //static reference
    final public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    //add room
    final public void addRoom(IRoom room) {
        rooms.add(room);
    }

    //get a room and return it to customer
    final public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    final public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    final public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : rooms) {
            if (!checkInDate.after(checkInDate) && !checkOutDate.before(checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    final public Collection<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (customer.getEmail().equals(reservation.getCustomer().getEmail())) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }


    final public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<IRoom> getRooms() {
        return rooms;
    }

}
