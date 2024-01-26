package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private static ReservationService reservationService;
    public List<Reservation> reservations = new ArrayList<Reservation>();
    public List<IRoom> rooms = new ArrayList<>();

    private ReservationService() {
    }
    //static reference
    public static ReservationService getInstance(){
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    //add room
    public void addRoom(IRoom room){
        rooms.add(room);
    }

    //get a room and return it to customer
    public IRoom getARoom(String roomId){
        for(IRoom room : rooms){
            if (room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom>findRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : rooms) {
            if(!checkInDate.after(checkInDate) && !checkOutDate.before(checkOutDate)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        for (Reservation reservation : reservations) {
            if(customer.getEmail().equals(reservation))
                return (Collection<Reservation>) reservation;
        }
        return null;
    }


    public void printAllReservations(){
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<IRoom> getRooms(){
        return rooms;
    }

}
