package model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private RoomInterface room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, RoomInterface room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString(){
        return "Customer: " + customer + "RoomInterface: " + room + "checkin: " + checkInDate + "Checkout: " + checkOutDate;
    }

}